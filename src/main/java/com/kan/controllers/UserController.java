package com.kan.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kan.dto.UserDto;
import com.kan.entity.User;
import com.kan.services.UserService;
 
@RestController	
public class UserController {

	@Autowired
	@Qualifier("userService")
	UserService userService;
   
	@RequestMapping(value = { "/checkUser" }, method = RequestMethod.POST)
	public ResponseEntity<Long> checkUser(@RequestBody User user) {
		
		String username = user.getUsername();
		String password = user.getPassword();
 
		User fetchedUser = userService.findOne(username, password);
		

		if (fetchedUser != null) {
			return new ResponseEntity<Long>(fetchedUser.getId(), HttpStatus.OK);
		} else {
			return 
			new ResponseEntity<Long>(HttpStatus.NOT_FOUND);
//			new ResponseEntity<Long>(fetchedUser.getId(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = { "/getUser/{id}" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<User> checkUser(@PathVariable("id") Long id) {

		User user = userService.findOne(id);  
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveUser" }, method = RequestMethod.POST, produces = "application/json")
 	public ResponseEntity<User> create(@RequestBody User user) {

		Boolean isExist = userService.checkUserName(user.getUsername());

		if (isExist) {
			return new ResponseEntity<User>(HttpStatus.CONFLICT);
		}

		try {
			User newUser = new User();
			newUser = user;
			newUser.setCreatedAt(new Date());
			newUser.setUpdateddAt(null);
			newUser.setCreatedBy(null);
			newUser.setUpdatedBy(null); 
//			newUser.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
//			newUser.setRole(form.getRole());
	       
			
			userService.saveUser(newUser);
		} catch (Exception ex) {
			return null;
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = { "/deleteUser" }, method = RequestMethod.POST, produces = "application/json")
 	public ResponseEntity<User> delete(@RequestBody List<Long> userIdList) {
 
		List<User> willBeRemovedUser = new ArrayList<User>();
		User fetchedUser = null;
		
		for (Long userId : userIdList) {
    		 fetchedUser = userService.findOne(userId);
			 willBeRemovedUser.add(fetchedUser);
		} 
 
		if (fetchedUser == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
 	
		try {			
				for (User user : willBeRemovedUser) {
					userService.delete(user);
				}
		} catch (Exception ex) {
			return new ResponseEntity<User>(HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<User>(HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/updateUser" }, method = RequestMethod.POST, produces = "application/json")
 	public ResponseEntity<User> updateUser(@RequestBody User user) {
		try {
//			newUser.setPassword(new BCryptPasswordEncoder().encode(form.getPassword()));
			
			userService.updateUser(user);
		} catch (Exception ex) {
			return null;
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	 
	@RequestMapping(value = { "/getUserList" }, method = RequestMethod.POST)
	public ResponseEntity<UserDto> getUserList(@RequestBody SearchQuery searchQuery) {

		List<User> userList = null;
		Long count = null;
		UserDto userDto = null;
		try {
			userList = userService.getUserList(searchQuery.getStart(), searchQuery.getOffset());
		    count = userService.getUserListCount();
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<UserDto>(HttpStatus.BAD_REQUEST);
		}
		
		userDto = new UserDto();
		userDto.setResultSet(userList);
		userDto.setTotalRecords(count);
		
		return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
	}
	
	@RequestMapping(value = { "/getUserCount" })
	public ResponseEntity<Long> getUserCount() {

		Long userCount = userService.getUserListCount();
		return new ResponseEntity<Long>(userCount, HttpStatus.OK);
	}
	
	 
 
 
 
	
	 
	
	@RequestMapping(value = { "/getUserById/{id}" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<User> getkUser(@PathVariable("id") Long id) {
		User user = userService.findOne(id);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	class BloodTypesDto {

		int value;
		String name;

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

	}

	 
}
