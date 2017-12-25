package com.kan.dto;

import java.util.List;

import com.kan.entity.Event;

public class EventDto {
	private Long TotalRecords;
	private List<Event> resultSet;

	public Long getTotalRecords() {
		return TotalRecords;
	}

	public void setTotalRecords(Long totalRecords) {
		TotalRecords = totalRecords;
	}

	public List<Event> getResultSet() {
		return resultSet;
	}

	public void setResultSet(List<Event> resultSet) {
		this.resultSet = resultSet;
	}
}
