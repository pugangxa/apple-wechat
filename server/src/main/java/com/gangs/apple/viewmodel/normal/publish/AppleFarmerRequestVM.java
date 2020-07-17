package com.gangs.apple.viewmodel.normal.publish;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AppleFarmerRequestVM {
	private Integer id;
	@NotNull
	private String name;
    @NotNull
    private String phone;
    
    private String location;

    private String category;

    private Integer quantity;

}
