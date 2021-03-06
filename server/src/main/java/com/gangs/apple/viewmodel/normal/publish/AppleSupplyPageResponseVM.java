package com.gangs.apple.viewmodel.normal.publish;

import lombok.Data;

@Data
public class AppleSupplyPageResponseVM {
    private Integer status;
	
    private Integer id;

    private Integer type;

    private Integer quantity;

    private Double price;

    private String phone;

    private String createTime;

    private String createUserName;

    private String description;

}
