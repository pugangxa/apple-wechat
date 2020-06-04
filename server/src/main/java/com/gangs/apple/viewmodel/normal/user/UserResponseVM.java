package com.gangs.apple.viewmodel.normal.user;

import com.gangs.apple.domain.User;
import com.gangs.apple.utility.DateTimeUtil;
import com.gangs.apple.viewmodel.BaseVM;
import lombok.Data;

@Data
public class UserResponseVM extends BaseVM {

    private Integer id;

    private String userUuid;

    private String userName;

    private String realName;

    private Integer age;

    private Integer role;

    private Integer sex;

    private String phone;

    private String createTime;

    private String modifyTime;

    private Integer status;

    private Integer userType;

    public static UserResponseVM from(User user) {
        UserResponseVM vm = modelMapper.map(user, UserResponseVM.class);
        vm.setCreateTime(DateTimeUtil.dateFormat(user.getCreateTime()));
        vm.setModifyTime(DateTimeUtil.dateFormat(user.getModifyTime()));
        return vm;
    }
}
