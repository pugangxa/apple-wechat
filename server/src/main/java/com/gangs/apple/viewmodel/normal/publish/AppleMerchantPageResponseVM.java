package com.gangs.apple.viewmodel.normal.publish;

import lombok.Data;

@Data
public class AppleMerchantPageResponseVM {
    private Integer status;
	
    private Integer id;

    private String name;

    private String location;

    private String category;

    private Integer quantity;

    private String phone;

    private String createTime;

    private String createUserName;  
}
