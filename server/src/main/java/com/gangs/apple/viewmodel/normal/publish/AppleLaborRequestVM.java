package com.gangs.apple.viewmodel.normal.publish;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AppleLaborRequestVM {
	private Integer id;
    @NotNull
    private Integer type;

    private Integer number;

    private Integer pay;
    @NotNull
    private String phone;
    @NotNull
    private String description;
}
