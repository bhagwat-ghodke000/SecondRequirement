package com.Requriment.UserService.service;

import com.Requriment.UserService.dto.UserDto;

import java.util.List;

public interface userI {

    UserDto registerUser(UserDto userDto);

    UserDto getUser(long userId);

    List<UserDto> getAllUser();

    UserDto updateUserDetails(long userId,UserDto userDto);

    List<UserDto> findByCountryName(String CountryName);

    List<UserDto> findByStateName(String stateName);

    UserDto findByAadhaarNumber(String AadhaarNo);

    UserDto findByPanCardNumber(String pancardNumber);

    UserDto findByMobileNumber(String mobileNumber);

    UserDto findByEmail(String email);
}
