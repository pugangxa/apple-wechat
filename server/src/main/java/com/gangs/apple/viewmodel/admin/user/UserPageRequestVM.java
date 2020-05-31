package com.gangs.apple.viewmodel.admin.user;

import com.gangs.apple.base.BasePage;
import lombok.Data;

@Data
public class UserPageRequestVM extends BasePage {

    private String userName;
    private Integer role;

}
