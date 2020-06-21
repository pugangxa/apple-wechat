package com.gangs.apple.viewmodel.normal.publish;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AppleSupplyRequestVM {
    @NotNull
    private Integer type;

    private Integer quantity;

    private Integer price;
    @NotNull
    private String phone;
    @NotNull
    private String description;
}
