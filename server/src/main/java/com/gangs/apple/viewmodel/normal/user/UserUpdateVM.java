package com.gangs.apple.viewmodel.normal.user;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateVM {

    @NotBlank
    private String realName;

    private String age;

    private Integer sex;

    private String phone;

    @NotNull
    private Integer userType;
}
