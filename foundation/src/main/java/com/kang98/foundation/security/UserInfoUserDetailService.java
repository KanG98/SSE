package com.kang98.foundation.security;

import com.kang98.data.dataauth.entity.User;
import com.kang98.data.dataauth.repository.UserRepository;
import com.kang98.foundation.security.UserInfoUserDetailsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@EnableJpaRepositories("com.kang98.data.dataauth.repository")
public class UserInfoUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = repository.findByName(username);
        System.out.println(userInfo);
        return userInfo.map(UserInfoUserDetailsMapper::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }

    public String addUser(User userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        repository.save(userInfo);
        return "user added to system ";
    }
}