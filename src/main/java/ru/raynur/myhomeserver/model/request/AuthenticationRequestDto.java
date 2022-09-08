package ru.raynur.myhomeserver.model.request;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
