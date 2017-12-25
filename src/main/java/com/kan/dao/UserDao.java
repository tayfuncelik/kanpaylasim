package com.kan.dao;

import java.util.List;

import com.kan.entity.User;

public interface UserDao {

	List<User> findAllUsers();// extends JpaRepository<User, Long>

	void delete(User user);

	User findOne(Long id);

	User saveUser(User user);

	User updateUser(User user);

	User findOne(String username, String password);

	Boolean checkUserName(String username);

	List<User> getUserList(int start, int offSet);

	Long getUserListCount();

}
