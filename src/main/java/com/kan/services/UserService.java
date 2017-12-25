package com.kan.services;

import java.util.List;

import com.kan.entity.User;

public interface UserService {

	public List<User> findAllUsers();

	public void delete(User user);

	public User findOne(Long id);

	public User saveUser(User user);

	public User findOne(String username, String password);

	public Boolean checkUserName(String username);

	public List<User> getUserList(int start, int offSet);

	public Long getUserListCount();

	public User updateUser(User user);

}