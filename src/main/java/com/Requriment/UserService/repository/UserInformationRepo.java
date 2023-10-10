package com.Requriment.UserService.repository;

import com.Requriment.UserService.entity.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInformationRepo extends JpaRepository<UserInformation,Long> {
}
