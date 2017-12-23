package com.kan.dto;

import java.util.List;

import com.kan.entity.User;

public class UserDto {

	private Long TotalRecords;
	private List<User> resultSet;

	public Long getTotalRecords() {
		return TotalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		TotalRecords = totalRecords;
	}

	public List<User> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<User> resultSet) {
		this.resultSet = resultSet;
	}

}
