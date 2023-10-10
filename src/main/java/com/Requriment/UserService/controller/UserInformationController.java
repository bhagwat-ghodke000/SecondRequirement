package com.Requriment.UserService.controller;

import com.Requriment.UserService.dto.CVUploadResponse;
import com.Requriment.UserService.dto.UserInformationDto;
import com.Requriment.UserService.service.serviceImpl.UserInformationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/userInformation")
public class UserInformationController {

    @Autowired
    private UserInformationImpl userInformation;

    @Value("${userService.document.path}")
    private String documentUploadPath;
    @PostMapping("/{userId}")
    ResponseEntity<UserInformationDto> addUserInformation(@RequestBody UserInformationDto userInformationDto, @PathVariable long userId){
        UserInformationDto userInformationDto1 = this.userInformation.addUserInformation(userInformationDto, userId);
        return new ResponseEntity<>(userInformationDto1, HttpStatus.OK);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    ResponseEntity<UserInformationDto> getCurrentUser(@PathVariable long userInformationId){
        UserInformationDto userInformationDto = this.userInformation.getUserInformation(userInformationId);
        return new ResponseEntity<>(userInformationDto,HttpStatus.OK);
    }

    @PutMapping("/{userInformationId}")
    ResponseEntity<UserInformationDto> updateUserInformation(@RequestBody UserInformationDto userInformationDto, @PathVariable long userInformationId){
        UserInformationDto informationDto = this.userInformation.updateUserInformation(userInformationId, userInformationDto);
        return new ResponseEntity<>(informationDto,HttpStatus.OK);
    }

    @PostMapping("/uploadFile/{userInformationId}")
    ResponseEntity<CVUploadResponse> uploadDocument(@RequestParam("userDocument")MultipartFile file,@PathVariable long userInformationId) throws IOException {
        String uploadFile = this.userInformation.UploadFile(file, documentUploadPath);
        UserInformationDto userInformationDto = this.userInformation.getUserInformation(userInformationId);
        userInformationDto.setUploadCV(uploadFile);
        UserInformationDto informationDto = this.userInformation.updateUserInformation(userInformationId, userInformationDto);
        CVUploadResponse uploadFileSecularly = CVUploadResponse.builder().FileName(uploadFile).message("Upload File Secularly").status(HttpStatus.CREATED).success(true).build();
        return new ResponseEntity<>(uploadFileSecularly,HttpStatus.OK);
    }
}
