package com.example.security.dto.mapper;

import com.example.security.dto.UserResponse;
import com.example.security.model.User;

import java.util.Optional;

public final class UserResponseMapper {
    private UserResponseMapper() {}

    public static Optional<UserResponse> mapToUserResponse(User user){
        if (user==null){
            return Optional.empty();
        }
        UserResponse userResponse=new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEnabled(user.isEnabled());
        userResponse.setAuthorities(user.getAuthorities());
        userResponse.setRole(user.getRole());

        return Optional.of(userResponse);
    }

    public static Optional<User> mapToUserUserEntity(UserResponse user){
        if (user==null){
            return Optional.empty();
        }
        User rv=new User();
        rv.setUsername(user.getUsername());
        rv.setEnabled(user.isEnabled());
        rv.setAuthorities(user.getAuthorities());
        rv.setRole(user.getRole());

        return Optional.of(rv);
    }

}
