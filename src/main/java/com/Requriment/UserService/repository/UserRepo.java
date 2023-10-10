package com.Requriment.UserService.repository;

import com.Requriment.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<User,Long> {

      User findByEmailId(String emailId);

      List<User> findByCountry(String countryName);

      List<User> findByState(String stateName);

      User findByAadharCardNumber(String AadhaarNo);

      User findByPanCardNumber(String PancardNumber);

      User findByMobileNumber(String mobileNumber);
}
