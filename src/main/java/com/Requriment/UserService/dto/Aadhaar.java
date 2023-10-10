package com.Requriment.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Aadhaar {

    private long aadhaarId;

    private String aadhaarCardNo;

    private String dateOfBirth;
}
