package com.kan.dto;

import java.util.List;

import com.kan.entity.Donor;

public class DonorDto {
	private Long TotalRecords;
	private List<Donor> resultSet;

	public Long getTotalRecords() {
		return TotalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		TotalRecords = totalRecords;
	}

	public List<Donor> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<Donor> resultSet) {
		this.resultSet = resultSet;
	}

}
