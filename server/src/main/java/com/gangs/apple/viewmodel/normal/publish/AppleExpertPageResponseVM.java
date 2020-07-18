package com.gangs.apple.viewmodel.normal.publish;

import lombok.Data;

@Data
public class AppleExpertPageResponseVM {
    private Integer status;
	
    private Integer id;
    
    private String name;

    private String expert;

    private String email;

    private String phone;

    private String createTime;

    private String createUserName;

}
