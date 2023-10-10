package com.Requriment.UserService.service.serviceImpl;

import com.Requriment.UserService.dto.UserInformationDto;
import com.Requriment.UserService.entity.User;
import com.Requriment.UserService.entity.UserInformation;
import com.Requriment.UserService.exception.BadRequestException;
import com.Requriment.UserService.exception.ResourceNotFoundException;
import com.Requriment.UserService.repository.UserInformationRepo;
import com.Requriment.UserService.repository.UserRepo;
import com.Requriment.UserService.service.UserInformationI;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserInformationImpl implements UserInformationI {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserInformationRepo userInformationRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public UserInformationDto addUserInformation(UserInformationDto userInformationDto,long userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User Not Found"));
        UserInformation map = this.modelMapper.map(userInformationDto, UserInformation.class);
        map.setUser(user);
        UserInformation save = this.userInformationRepo.save(map);
        UserInformationDto map1 = this.modelMapper.map(save, UserInformationDto.class);
        return map1;
    }

    @Override
    public UserInformationDto getUserInformation(long userInformationId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//        User user = this.userRepo.findByEmailId(name);
//        List<UserInformation> all = this.userInformationRepo.findAll();

        UserInformation userInformation = this.userInformationRepo.findById(userInformationId).orElseThrow(() -> new ResourceNotFoundException("User Information is not found"));
        UserInformationDto map = this.modelMapper.map(userInformation, UserInformationDto.class);
        return map;
    }

    @Override
    public UserInformationDto updateUserInformation(long userInformationId, UserInformationDto userInformationDto) {
        UserInformation userInformation = this.userInformationRepo.findById(userInformationId).orElseThrow(() -> new ResourceNotFoundException("UserInformation is not found"));
        userInformation.setCompanyName(userInformationDto.getCompanyName());
        userInformation.setCTC(userInformationDto.getCTC());
        userInformation.setAchievements(userInformationDto.getAchievements());
        userInformation.setExistDate(userInformationDto.getExistDate());
        userInformation.setJoiningDate(userInformationDto.getJoiningDate());
        userInformation.setUploadCV(userInformationDto.getUploadCV());
        UserInformation save = this.userInformationRepo.save(userInformation);
        UserInformationDto map = this.modelMapper.map(save, UserInformationDto.class);
        return map;
    }

    @Override
    public String UploadFile(MultipartFile file, String path) throws IOException {

        String originalFileName = file.getOriginalFilename();
        String fileName = UUID.randomUUID().toString();
        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String fileNameWithExtension = fileName + extension;
        String fullPath = path + fileNameWithExtension;

        if(extension.equalsIgnoreCase(".pdf") || extension.equalsIgnoreCase(".docx")){

            File folder = new File(path);

            if(!folder.exists()){
                folder.mkdirs();
            }

            Files.copy(file.getInputStream(), Paths.get(fullPath));
            return fileNameWithExtension;
        }else {
            throw new BadRequestException("Upload only pdf and docs file..");
        }
    }
}
