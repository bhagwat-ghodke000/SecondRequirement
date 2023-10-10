package com.Requriment.UserService.service;

import com.Requriment.UserService.dto.UserInformationDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserInformationI {

    UserInformationDto addUserInformation(UserInformationDto userInformationDto,long userId);

    UserInformationDto getUserInformation(long userInformationId);

    UserInformationDto updateUserInformation(long userInformationId,UserInformationDto userInformationDto);

    String UploadFile(MultipartFile file,String path) throws IOException;
}
