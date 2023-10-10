package com.Requriment.UserService.controller;


import com.Requriment.UserService.dto.JwtResponse;
import com.Requriment.UserService.dto.UserDto;
import com.Requriment.UserService.dto.UserLogin;
import com.Requriment.UserService.security.JwtHelper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtHelper helper;

    public ResponseEntity<UserDto> getCurrentUser(Principal principal){
        String name = principal.getName();
        return new ResponseEntity<>(modelMapper.map(userDetailsService.loadUserByUsername(name),UserDto.class), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody UserLogin request){
        this.doAuthenticate(request.getEmailId(),request.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmailId());
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        String s = this.helper.generateToken(userDetails);
        JwtResponse jwtResponse = JwtResponse.builder()
                .jwtToken(s)
                .user(userDto)
                .build();

        return new ResponseEntity<JwtResponse>(jwtResponse,HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email,password);

        try {
            manager.authenticate(authentication);
        }catch (BadCredentialsException e){
            throw new RuntimeException("In Correct Email and Password Please Check");
        }
    }
}
