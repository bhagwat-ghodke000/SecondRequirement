package com.Requriment.UserService.configuration;

import com.Requriment.UserService.dto.UserDto;
import com.Requriment.UserService.entity.User;
import com.Requriment.UserService.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class UserIntilizer implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(UserIntilizer.class);

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public void run(String... args) throws Exception {

        if(userRepo.findByEmailId("root12345@gmail.com")==null){
            UserDto user = new UserDto();
            user.setEmailId("root12345@gmail.com");
            logger.info("This is a email "+ user.getEmailId());
            user.setPassword("Root@12345");
            user.setPinCode("413515");
            user.setCity("Pune");
            user.setDistrict("Pune");
            user.setCountry("India");
            user.setState("Maharashtra");
            user.setUserType("NormalUser");
            user.setPanCardNumber("CMSPJ0128C");
            user.setMobileNumber("9112958912");
            user.setAadharCardNumber("3275 9854 6012");
            user.setConfirmPassword("Root@12345");
            User map = this.modelMapper.map(user, User.class);
            userRepo.save(map);
        }
    }
}
