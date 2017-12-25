package com.kan.daoImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.kan.dao.AbstractDao;
import com.kan.dao.UserDao;
import com.kan.entity.User;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		List<User> users = (List<User>) getEntityManager().createQuery("select u from User u").getResultList();
		return users;
	}

	public User saveUser(User user) {
		try {
			getEntityManager().persist(user);
			return findOne(Long.valueOf(String.valueOf(user.getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public void delete(User user) {
		getEntityManager().remove(user);
	}

	@SuppressWarnings("unchecked")
	public User findOne(Long id) {
		List<User> userList = (List<User>) getEntityManager().createQuery("select u from User u where u.id = :id")
				.setParameter("id", id).getResultList();

		if (1 <= userList.size()) {
			return userList.get(0);
		}

		return null;
	}

	@Override
	public User findOne(String username, String password) {
		User user = (User) getEntityManager()
				.createQuery("from User u where u.username = :username and u.password = :password", User.class)
				.setParameter("username", username).setParameter("password", password).getResultList().stream()
				.findFirst().orElse(null);

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Boolean checkUserName(String username) {
		Long count = (Long) getEntityManager()
				.createQuery("select count(u.Id) from User u where u.username = :username ")
				.setParameter("username", username).getResultList().stream().findFirst().orElse(null);

		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserList(int start, int offSet) {
		List<User> users = (List<User>) getEntityManager().createQuery("select u from User u").setFirstResult(start)
				.setMaxResults(offSet).getResultList();
		return users;
	}

	@Override
	public Long getUserListCount() {
		Long count = (Long) getEntityManager().createQuery("select count(u.id) from User u").getSingleResult();
		return count;
	}

	@Override
	public User updateUser(User user) {
		try {
			getEntityManager().merge(user);
			return findOne(Long.valueOf(String.valueOf(user.getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

}