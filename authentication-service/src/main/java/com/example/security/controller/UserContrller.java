package com.example.security.controller;

import com.example.security.config.JwtTokenProvider;
import com.example.security.dto.UserRequest;
import com.example.security.dto.UserResponse;
import com.example.security.model.Authority;
import com.example.security.model.User;
import com.example.security.model.enums.AuthorityTypes;
import com.example.security.model.enums.Role;
import com.example.security.service.CodeService;
import com.example.security.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserContrller {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final CodeService codeService;

    public UserContrller(UserService userService, JwtTokenProvider jwtTokenProvider, CodeService codeService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.codeService = codeService;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserRequest userRequest) {
        Optional<UserResponse> user = userService.getByLoginAndPassword(userRequest.getUsername(), userRequest.getPassword());
        if (user.isPresent() && user.get().isEnabled()) {
            user.get().setActive(true);
            return jwtTokenProvider.jwtTockenCreator(user.get());
        }
        return null;
    }

    @PostMapping("/register")
    public ResponseEntity registration(@RequestBody UserRequest userRequest, @RequestParam String userType) {
        User user = new User(userRequest.getUsername(), userRequest.getPassword());
        if (!userService.getByLogin(user.getUsername()).isPresent()) {
            if ("USER".equals(userType)) {
                user.setRole(Role.ROLE_USER);
                user.getAuthorities().add(new Authority(new Authority.AuthorityID(userRequest.getUsername(), AuthorityTypes.USER_ACTIONS.toString())));
            } else if ("COMPANY".equals(userType)) {
                user.setRole(Role.ROLE_COMPANY);
                user.getAuthorities().add(new Authority(new Authority.AuthorityID(userRequest.getUsername(), AuthorityTypes.COMPANY_ACTIONS.toString())));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }
            user.setEnabled(false);
            user.setActive(false);
            userService.update(user);
            return ResponseEntity.of(Optional.of(user));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user with " + user.getUsername() + " username alredy exist");
    }

    @PostMapping("/verify/{username}")
    public ResponseEntity verify(@PathVariable String username, @RequestBody String code) {
        if (codeService.isAccessed(username, code)) {
            UserResponse user = userService.getByLogin(username).get();
            user.setEnabled(true);
            userService.update(user);
            return ResponseEntity.status(HttpStatus.OK).body("User verification complite");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User verification failed");
    }

    @DeleteMapping("/user/{username}")
    public ResponseEntity delete(@PathVariable String username) {
        if (userService.delete(username)){
            return ResponseEntity.status(HttpStatus.OK).body("Delete is complit");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
    }

    @GetMapping("logout")
    public void logout(String username){
        Optional<UserResponse> user=userService.getByLogin(username);
        if (user.isPresent() && user.get().isActive() ){
            user.get().setActive(false);
            userService.update(user.get());
        }

    }


}
