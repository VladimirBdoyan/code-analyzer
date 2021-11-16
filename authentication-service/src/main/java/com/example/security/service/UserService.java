package com.example.security.service;

import com.example.security.dto.UserResponse;
import com.example.security.dto.mapper.UserRequestMapper;
import com.example.security.dto.mapper.UserResponseMapper;
import com.example.security.model.Authority;
import com.example.security.model.User;
import com.example.security.repository.AuthorityRepository;
import com.example.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(false);
        return userRepository.save(user);
    }

    public Optional<UserResponse> update(User user){
        if (user==null){
            return Optional.empty();
        }
        return UserResponseMapper.mapToUserResponse(userRepository.save(user));
    }

    public boolean delete(String username) {

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            return true;
        }
        return false;
    }

    public Optional<UserResponse> update(UserResponse user) {
        if (user==null){
            return Optional.empty();
        }
        return UserResponseMapper.mapToUserResponse(userRepository.save(UserResponseMapper.mapToUserUserEntity(user).get()));
    }

    public Optional<User> get(String username) {
        return userRepository.findByUsernameEager(username);
    }

    public Optional<UserResponse> getByLogin(String login) {

        return UserResponseMapper.mapToUserResponse(userRepository.findByUsernameEager(login).get());
    }

    public Optional<UserResponse> getByLoginAndPassword(String login, String password) {
        User user = userRepository.findByUsername(login);
        if (user!=null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return UserResponseMapper.mapToUserResponse(user);
            }
        }
        return Optional.empty();
    }
}