package com.gangs.apple.viewmodel.normal.publish;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AppleSupplyRequestVM {
	private Integer id;
    @NotNull
    private Integer type;

    private Integer quantity;

    private Double price;
    @NotNull
    private String phone;
    @NotNull
    private String description;
}
