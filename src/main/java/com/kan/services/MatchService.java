package com.kan.services;

import java.util.Map;

import javax.swing.SortOrder;

import com.kan.dto.EventDto;
import com.kan.entity.Donor;

public interface MatchService {

	EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class entityClass);

	Donor findOne(Long id);
}
