package com.Requriment.UserService.dto;

import com.Requriment.UserService.entity.User;
import lombok.*;

import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInformationDto {
    private long userIdInformationId;

    private String companyName;

    private Date joiningDate;

    private Date existDate;

    private String achievements;

    private String CTC;

    private String uploadCV;

    private User user;
}
