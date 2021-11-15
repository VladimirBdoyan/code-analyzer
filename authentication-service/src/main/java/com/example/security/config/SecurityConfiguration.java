package com.example.security.config;

import com.example.security.repository.UserRepository;
import com.example.security.security.JwtAuthenticationFilter;
import com.example.security.security.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfiguration(UserDetailsService userDetailsService, UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // remove csrf and state in session, because in JWT we don't need them
                // the order of antMatchers is important
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // add jwt filters (1. authentication, 2. authorization)
                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtTokenProvider))
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), this.userRepository))
               .authorizeRequests()
                // configure access rules
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/Autentication").authenticated()
                .antMatchers("/admins/*").hasRole("ADMIN")
                .antMatchers("/users/*").hasAnyRole("USER")
                .and()
                .logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");
    }

    private DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
