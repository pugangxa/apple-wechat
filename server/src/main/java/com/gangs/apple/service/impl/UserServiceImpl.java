package com.gangs.apple.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gangs.apple.domain.User;
import com.gangs.apple.domain.other.KeyValue;
import com.gangs.apple.exception.BusinessException;
import com.gangs.apple.repository.UserMapper;
import com.gangs.apple.service.UserService;
import com.gangs.apple.viewmodel.admin.user.UserPageRequestVM;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {
	private static final String DefaultPass = "C/iK1+hGVfLVqdtgngfXfG2mH+NEfkD6zB2ybqxmH7ikBzQ6QS1cuQ6oiHblFPSaP1MMXMTNlfZRPNJg//65RW8/NDSCE7gTEEUgOsSXbyttzIub2BSQOhpKHrC150BZq8RttAdWLl3pokKFQjXiTfbfW6j3+8A+N3/mJv0aa9I=";

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, ApplicationEventPublisher eventPublisher) {
        super(userMapper);
        this.userMapper = userMapper;
    }


    @Override
    public List<User> getUsers() {
        return userMapper.getAllUser();
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByUserName(String username) {
        return userMapper.getUserByUserName(username);
    }

    @Override
    public int insertByFilter(User record) {
        return super.insertByFilter(record);
    }

    @Override
    public int updateByIdFilter(User record) {
        return super.updateByIdFilter(record);
    }

    @Override
    public int updateById(User record) {
        return super.updateById(record);
    }

    @Override
    public User getUserByUserNamePwd(String username, String pwd) {
        return userMapper.getUserByUserNamePwd(username, pwd);
    }


    @Override
    public List<User> userPageList(String name, Integer pageIndex, Integer pageSize) {
        Map<String, Object> map = new HashMap<>(3);
        map.put("name", name);
        map.put("offset", ((int) pageIndex) * pageSize);
        map.put("limit", pageSize);
        return userMapper.userPageList(map);
    }

    @Override
    public Integer userPageCount(String name) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("name", name);
        return userMapper.userPageCount(map);
    }


    @Override
    public PageInfo<User> userPage(UserPageRequestVM requestVM) {
        return PageHelper.startPage(requestVM.getPageIndex(), requestVM.getPageSize(), "id desc").doSelectPageInfo(() ->
                userMapper.userPage(requestVM)
        );
    }


    @Override
    public void insertUser(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    @Transactional(rollbackFor = BusinessException.class)
    public void insertUsers(List<User> users) {
        userMapper.insertUsers(users);
        throw new BusinessException("Cannot insert user to database");
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public void updateUsersAge(Integer age, List<Integer> ids) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("idslist", ids);
        map.put("age", age);
        userMapper.updateUsersAge(map);
    }

    @Override
    public void deleteUserByIds(List<Integer> ids) {
        userMapper.deleteUsersByIds(ids);
    }

    @Override
    public Integer selectAllCount() {
        return userMapper.selectAllCount();
    }

    @Override
    public List<KeyValue> selectByUserName(String userName) {
        return userMapper.selectByUserName(userName);
    }

    @Override
    public List<User> selectByIds(List<Integer> ids) {
        return userMapper.selectByIds(ids);
    }


	@Override
	public void resetPassById(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		user.setPassword(DefaultPass);
		userMapper.updateByPrimaryKeySelective(user);
	}
}
