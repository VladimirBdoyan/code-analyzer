package com.example.security.service;

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

    public void delete(String username) {

        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByUsername(username));
        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());
            for (Authority authority : optionalUser.get().getAuthorities()) {
                authorityRepository.save(authority);
            }
        }
    }

    public User update(User user) {
        return userRepository.save(user);
    }

    public Optional<User> get(String username) {
        return userRepository.findByUsernameEager(username);
    }

    public Optional<User> getByLogin(String login) {
        return userRepository.findByUsernameEager(login);
    }

    public Optional<User> getByLoginAndPassword(String login, String password) {
        Optional<User> user = getByLogin(login);
        if (user.isPresent()) {
            if (passwordEncoder.matches(password, user.get().getPassword())) {
                return user;
            }
        }
        return Optional.empty();
    }
}