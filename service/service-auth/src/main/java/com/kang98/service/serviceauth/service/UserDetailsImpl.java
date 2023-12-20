package com.kang98.service.serviceauth.service;

import com.kang98.service.serviceauth.entity.User;
import com.kang98.service.serviceauth.repository.UserRepository;
import com.kang98.service.serviceproduct.service.helpers.UserDetailsHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userInfo = userRepository.findByName(username);
        System.out.println(userInfo);
        return userInfo.map(UserDetailsHelper::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found "+username));
    }

//    public String addUser(User userInfo) {
//        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
//        repository.save(userInfo);
//        return "user added to system ";
//    }
}
