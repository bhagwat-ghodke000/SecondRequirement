package com.Requriment.UserService.service.serviceImpl;

import com.Requriment.UserService.dto.Aadhaar;
import com.Requriment.UserService.dto.UserDto;
import com.Requriment.UserService.entity.User;
import com.Requriment.UserService.exception.BadRequestException;
import com.Requriment.UserService.exception.ResourceNotFoundException;
import com.Requriment.UserService.repository.UserRepo;
import com.Requriment.UserService.service.userI;
import com.Requriment.UserService.twiloconfiguration.TwilloConfigoration;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class userImpl implements userI {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestTemplate restTemplate;

    public static final String ACCOUNT_SID ="AC3d88b6c1f5e3eb6feefd9b354a4c7000";

    public static final String AUTH_TOKEN = "d82a03ee6c2ba89f25d64ff0dae4b119";

    public static final String FROM_NUMBER = "+12563611803";

    public static final String Message1 = "User Register Successfully";
    @Override
    public UserDto registerUser(UserDto userDto) {

        Aadhaar forObject = restTemplate.getForObject("http://localhost:8081/aadhaar/" + userDto.getAadharCardNumber(), Aadhaar.class);
        System.out.println("Date of Birth is :"+forObject.getDateOfBirth());
        if(forObject.getAadhaarCardNo().equals(userDto.getAadharCardNumber())) {
            User map = this.modelMapper.map(userDto, User.class);
            User save = this.userRepo.save(map);
            UserDto map1 = this.modelMapper.map(save, UserDto.class);

            String prefixChar = "+91";
            String newstring = prefixChar + save.getMobileNumber();

            try {
                Twilio.init(TwilloConfigoration.ACCOUNT_SID, TwilloConfigoration.AUTH_TOKEN);
                Message message = Message.creator(new PhoneNumber(newstring), new PhoneNumber(FROM_NUMBER), Message1)
                        .create();
            } catch (Exception e) {
                throw new RuntimeException("Twillo Error" + e);
            }
            return map1;
        }else {
            throw new BadRequestException("Please fill the correct Aadhaar card number..");
        }
    }

    @Override
    public UserDto getUser(long userId) {
        User thisUserIsNotFound = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User is a not found"));
        UserDto map = this.modelMapper.map(thisUserIsNotFound, UserDto.class);
        return map;
    }

    @Override
    public List<UserDto> getAllUser() {
        List<User> all = this.userRepo.findAll();
        List<UserDto> userDtos = all.stream().map(a -> this.modelMapper.map(a, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto updateUserDetails(long userId, UserDto userDto) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Use is not Found"));
        user.setEmailId(userDto.getEmailId());
        user.setMobileNumber(userDto.getMobileNumber());
        user.setCity(userDto.getCity());
        user.setUserType(userDto.getUserType());
        user.setDistrict(userDto.getDistrict());
        user.setPassword(userDto.getPassword());
        user.setAadharCardNumber(userDto.getAadharCardNumber());
        user.setPanCardNumber(userDto.getPanCardNumber());
        user.setPinCode(userDto.getPinCode());
        user.setState(userDto.getState());
        user.setCountry(user.getCountry());
        User save = this.userRepo.save(user);
        UserDto map = this.modelMapper.map(save, UserDto.class);
        return map;
    }

    @Override
    public List<UserDto> findByCountryName(String CountryName) {
        List<User> byCountry = this.userRepo.findByCountry(CountryName);
        List<UserDto> userDtos = byCountry.stream().map(list -> this.modelMapper.map(list, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public List<UserDto> findByStateName(String stateName) {
        List<User> byState = this.userRepo.findByState(stateName);
        List<UserDto> userDtos = byState.stream().map(list -> this.modelMapper.map(list, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public UserDto findByAadhaarNumber(String AadhaarNo) {

        User aadharCardNumber = this.userRepo.findByAadharCardNumber(AadhaarNo);
        UserDto map = this.modelMapper.map(aadharCardNumber, UserDto.class);
        return map;
    }

    @Override
    public UserDto findByPanCardNumber(String pancardNumber) {
        User byPanCardNumber = this.userRepo.findByPanCardNumber(pancardNumber);
        UserDto map = this.modelMapper.map(byPanCardNumber, UserDto.class);
        return map;
    }

    @Override
    public UserDto findByMobileNumber(String mobileNumber) {

        User byMobileNumber = this.userRepo.findByMobileNumber(mobileNumber);
        UserDto map = this.modelMapper.map(byMobileNumber, UserDto.class);
        return map;
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = this.userRepo.findByEmailId(email);
        UserDto map = this.modelMapper.map(user, UserDto.class);
        return map;
    }
}
