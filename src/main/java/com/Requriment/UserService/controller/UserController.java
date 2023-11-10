package com.Requriment.UserService.controller;

import com.Requriment.UserService.configuration.UserIntilizer;
import com.Requriment.UserService.dto.UserDto;
import com.Requriment.UserService.service.serviceImpl.userImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private userImpl userimpl;

    @RequestMapping(value = "/",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto){
        logger.info("In the Controller");
        UserDto userDto1 = this.userimpl.registerUser(userDto);
        logger.info("After Complete Service");
        return new ResponseEntity<>(userDto1, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getSingleUser(@PathVariable long userId){
        logger.info("In the Controller");
        UserDto user = this.userimpl.getUser(userId);
        logger.info("after the Controller"+ user.toString());
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> allUser = this.userimpl.getAllUser();
        return new ResponseEntity<>(allUser,HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@PathVariable long userId,@Valid @RequestBody UserDto userDto){
        UserDto userDto1 = this.userimpl.updateUserDetails(userId, userDto);
        return new ResponseEntity<>(userDto1,HttpStatus.OK);
    }

    @GetMapping("/getUserByMobileNumber/{mobileNo}")
    ResponseEntity<UserDto> getUserByMobileNo(@PathVariable String mobileNo){

        UserDto byMobileNumber = this.userimpl.findByMobileNumber(mobileNo);
        return new ResponseEntity<>(byMobileNumber,HttpStatus.OK);
    }

    @GetMapping("/getUserByAadhaar/{aadhaarNo}")
    ResponseEntity<UserDto> getUserByAadhaarNo(@PathVariable String aadhaarNo){
        UserDto byAadhaarNumber = this.userimpl.findByAadhaarNumber(aadhaarNo);
        return new ResponseEntity<>(byAadhaarNumber,HttpStatus.OK);
    }

    @GetMapping("/getUserByEmail{email}")
    ResponseEntity<UserDto> getUserByEmail(String email){
        UserDto Email = this.userimpl.findByEmail(email);
        return new ResponseEntity<>(Email,HttpStatus.OK);
    }

    @GetMapping("/getUserByCountry/{countryName}")
    ResponseEntity<List<UserDto>> getUserByCountry(@PathVariable String countryName){
        List<UserDto> byCountryName = this.userimpl.findByCountryName(countryName);
        return new ResponseEntity<>(byCountryName,HttpStatus.OK);
    }

    @GetMapping("/getUserByState/{stateName}")
    ResponseEntity<List<UserDto>> getUserByState(@PathVariable String stateName){
        List<UserDto> byStateName = this.userimpl.findByStateName(stateName);
        return new ResponseEntity<>(byStateName,HttpStatus.OK);
    }

    @GetMapping("/getUserByPanCard/{pancardNo}")
    ResponseEntity<UserDto> getUserByPanCardNo(@PathVariable String pancardNo){
        UserDto byPanCardNumber = this.userimpl.findByPanCardNumber(pancardNo);
        return new ResponseEntity<>(byPanCardNumber,HttpStatus.OK);
    }
}
