package com.kan.dao;

import java.util.Map;

import javax.swing.SortOrder;

import com.kan.dto.EventDto;

public interface MatchDao {

	@SuppressWarnings("rawtypes")
	EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters,
			Class entityClass);

}
