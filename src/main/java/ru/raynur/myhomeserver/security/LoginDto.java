package ru.raynur.myhomeserver.security;

import lombok.Data;

@Data
public class LoginDto {
    private String username;
    private String password;
}
