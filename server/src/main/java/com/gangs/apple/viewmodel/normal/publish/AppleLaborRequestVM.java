package com.gangs.apple.viewmodel.normal.publish;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;
@Data
public class AppleLaborRequestVM {
    @NotNull
    private Integer type;

    private Integer number;

    private Integer pay;
    @NotNull
    private String phone;
    @NotNull
    private String description;
}
