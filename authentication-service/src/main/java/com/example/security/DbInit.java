package com.example.security;

//import com.example.security.model.Authority;
//import com.example.security.model.User;
//import com.example.security.model.enums.Role;
//import com.example.security.repository.AuthorityRepository;
//import com.example.security.repository.UserRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class DbInit implements CommandLineRunner {
//
//    private UserRepository userRepository;
//    public PasswordEncoder passwordEncoder;
//    private final AuthorityRepository authorityRepository;
//
//    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.authorityRepository = authorityRepository;
//    }
//
//    @Override
//    public void run(String... args) {
//
//        User user = new User();
//        user.setUsername("Hayk");
//        user.setPassword(passwordEncoder.encode("123456"));
//        user.setEnabled(true);
//        user.setRole(Role.ROLE_ADMIN);
//        userRepository.save(user);
//        Authority authority=new Authority(new Authority.AuthorityID("Hayk","ADMIN_ACTIONS"));
//        authorityRepository.save(authority);
//       user.getAuthorities().add(authority);
//        userRepository.save(user);
//
//    }
//}
//
