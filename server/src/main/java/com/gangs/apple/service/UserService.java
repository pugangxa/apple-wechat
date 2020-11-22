package com.gangs.apple.service;

import com.gangs.apple.domain.other.KeyValue;
import com.github.pagehelper.PageInfo;
import com.gangs.apple.viewmodel.admin.user.UserPageRequestVM;
import com.gangs.apple.domain.User;

import java.util.List;

public interface UserService extends BaseService<User> {

    /**
     * getUsers
     *
     * @return List<User>
     */
    List<User> getUsers();

    /**
     * getUserById
     *
     * @param id id
     * @return User
     */
    User getUserById(Integer id);

    /**
     * getUserByUserName
     *
     * @param username username
     * @return User
     */
    User getUserByUserName(String username);
    
    User getUserByOpenId(String openId);

    /**
     * getUserByUserName
     *
     * @param username username
     * @param pwd      pwd
     * @return User
     */
    User getUserByUserNamePwd(String username, String pwd);

    /**
     * insertUser
     *
     * @param user user
     */
    void insertUser(User user);

    /**
     * insertUsers
     *
     * @param users users
     */
    void insertUsers(List<User> users);

    /**
     * updateUsersAge
     *
     * @param age
     * @param ids
     */
    void updateUsersAge(Integer age, List<Integer> ids);
    
    /**
     * userPageList
     *
     * @param name      name
     * @param pageIndex pageIndex
     * @param pageSize  pageSize
     * @return List<User>
     */
    List<User> userPageList(String name, Integer pageIndex, Integer pageSize);

    /**
     * deleteUserByIds
     *
     * @param ids
     */
    void deleteUserByIds(List<Integer> ids);


    Integer selectAllCount();


    List<KeyValue> selectByUserName(String userName);


    List<User> selectByIds(List<Integer> ids);
    
    /**
     * userPageCount
     *
     * @param name name
     * @return Integer
     */
    Integer userPageCount(String name);


    /**
     * @param requestVM requestVM
     * @return PageInfo<User>
     */
    PageInfo<User> userPage(UserPageRequestVM requestVM);

	void resetPassById(Integer id);
}
