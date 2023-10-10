package com.Requriment.UserService.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CVUploadResponse {

    private String FileName;

    private String message;

    private HttpStatus status;
    private boolean success;
}
