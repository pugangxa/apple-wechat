package com.gangs.apple.viewmodel.normal.publish;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AppleExpertRequestVM {
	private Integer id;
	@NotNull
	private String name;
    @NotNull
    private String phone;
    
    private String expert;

    private String email;

}
