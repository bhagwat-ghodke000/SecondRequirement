package com.Requriment.UserService.dto;

import com.Requriment.UserService.entity.UserCountry;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private long userId;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$",message = "Please fill the correct emailId") @NotNull
    private String emailId;

    @Size(min = 8)
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[#$@!%&*?])[A-Za-z\\d#$@!%&*?]{8,}$",message = "Password at least 8 character 1 lower 1 upper 1 special character in the password")
    private String password;

    private String confirmPassword;

    @Digits(fraction = 0,integer = 6,message = "Pin Code is a 6 digit")
    private String pinCode;

    @Pattern(regexp = "^[2-9]{1}[0-9]{3}\\s[0-9]{4}\\s[0-9]{4}$", message="Fill the correct Aadhaar number")
    @NotNull(message = "Please Insert the Aadhaar no ")
    private String aadharCardNumber;

    @NotNull(message = "Fill the Pan Card Number")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]{1}")
    private String panCardNumber;

    private String city;

    @Digits(fraction = 0,integer = 10,message = "Number must be a 10 digit")
    private String mobileNumber;

    private String userType;

    private String country;

    private String state;

    private String district;

}
