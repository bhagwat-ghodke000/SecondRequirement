package com.Requriment.UserService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userIdInformationId;

    private String companyName;

    private Date joiningDate;

    private Date existDate;

    private String achievements;

    private String CTC;

    private String uploadCV;

    @ManyToOne
    private User user;
}
