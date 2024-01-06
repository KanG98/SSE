package com.kang98.service.serviceorder.config;

import com.kang98.service.serviceauth.config.AuthServiceConfig;
import com.kang98.foundation.security.JwtAuthFilter;
import com.kang98.foundation.security.UserInfoUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@ComponentScan({"com.kang98.data.dataauth.repository", "com.kang98.service.serviceproduct", "com.kang98.foundation.security"})
@Import(AuthServiceConfig.class)
public class OrderSecurityConfig {
    @Bean
    @Autowired
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authenticationProvider,
                                                   UserInfoUserDetailService userInfoUserDetailService,
                                                   PasswordEncoder passwordEncoder,
                                                   JwtAuthFilter authFilter) throws Exception {

        authenticationProvider.setUserDetailsService(userInfoUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/orders/**").authenticated()
                                .anyRequest().permitAll()
                )
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }
}
