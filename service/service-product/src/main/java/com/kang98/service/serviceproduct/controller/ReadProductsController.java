package com.kang98.service.serviceproduct.controller;

import com.kang98.data.dataproduct.entity.Product;
import com.kang98.service.serviceproduct.dto.AuthRequest;
import com.kang98.service.serviceproduct.dto.GetProductsRequest;
import com.kang98.service.serviceproduct.dto.GetProductsResponse;
import com.kang98.service.serviceproduct.entity.User;
import com.kang98.service.serviceproduct.service.JwtService;
import com.kang98.service.serviceproduct.service.ReadProductsService;
import com.kang98.service.serviceproduct.service.UserInfoUserDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.kang98.service.serviceproduct.config.ProductServiceEndpoints.READ_ALL_PRODUCTS_PATH;
import static com.kang98.service.serviceproduct.config.ProductServiceEndpoints.READ_PRODUCTS_BY_NAME;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ReadProductsController {

    private final ReadProductsService readProductsService;

    private final JwtService jwtService;

    private final UserInfoUserDetailService userInfoUserDetailService;

    private final AuthenticationManager authenticationManager;

    @PostMapping(READ_ALL_PRODUCTS_PATH)
//    @PreAuthorize("hasAuthority('ADMIN')") // only ADMIN can access it
    public GetProductsResponse getAllProducts() {
        log.info("Get all products called");
        List<Product> productList;
        try {
            productList = readProductsService.getAllProducts();
        }
        catch (IllegalArgumentException e) {
            log.error("Missing request field", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            log.error("Internal Server Error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage(), e);
        }
        return GetProductsResponse.builder().productList(productList).build();
    }

    @PostMapping(READ_PRODUCTS_BY_NAME)
    public GetProductsResponse getProductsByName(@RequestBody @Valid GetProductsRequest getProductsRequest) {
        log.info("Get products by name called: productName = " + getProductsRequest.getProductName());
        List<Product> productList;
        try {
            productList = readProductsService.getProductsByName(getProductsRequest.getProductName());
        }
        catch (IllegalArgumentException e) {
            log.error("Missing request field", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        catch (RuntimeException e) {
            log.error("Internal Server Error", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error: " + e.getMessage(), e);
        }
        return GetProductsResponse.builder().productList(productList).build();
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        else {
            throw new UsernameNotFoundException("invalid user request");
        }
    }

    @PostMapping("/new")
    public String addNewUser(@RequestBody User userInfo){
        return userInfoUserDetailService.addUser(userInfo);
    }
}