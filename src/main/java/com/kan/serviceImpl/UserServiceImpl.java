package com.kan.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kan.dao.UserDao;
import com.kan.entity.User;
import com.kan.services.UserService;

@Component
@Qualifier("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}

	@Override
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	public User findOne(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public User findOne(String username, String password) {
		return userDao.findOne(username, password);
	}

	@Override
	public Boolean checkUserName(String username) {
		return userDao.checkUserName(username);
	}

	@Override
	public List<User> getUserList(int start, int offSet) {
		return userDao.getUserList(start, offSet);
	}

	@Override
	public Long getUserListCount() {
		return userDao.getUserListCount();
	}

}
