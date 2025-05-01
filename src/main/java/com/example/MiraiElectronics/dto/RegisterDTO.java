package com.example.MiraiElectronics.dto;

import com.example.MiraiElectronics.repository.repositoryEnum.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private String username;
    private String password;
    private String email;
    private Role role;
}