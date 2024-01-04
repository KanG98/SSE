package com.kang98.service.servicedeliveryman.config;

import com.kang98.foundation.security.JwtAuthFilter;
import com.kang98.foundation.security.UserInfoUserDetailService;
import com.kang98.service.serviceauth.config.AuthServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
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
@ComponentScan({"com.kang98.data.dataauth.repository", "com.kang98.foundation.security"})
@EntityScan({"com.kang98.data.dataauth.entity"})
@Import(AuthServiceConfig.class)
public class DeliverymanSecurityConfig {

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
                .authorizeHttpRequests( auth ->
                        auth.requestMatchers("/deliveryman/**").authenticated()
                )
                .sessionManagement(c -> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
