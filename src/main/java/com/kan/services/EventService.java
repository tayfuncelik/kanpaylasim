package com.kan.services;

import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

import com.kan.dto.EventDto;
import com.kan.entity.Event;

public interface EventService {
	
	public void deleteEvet(Event event);

	public Event findOne(Long id);

	public Event saveEvent(Event event);

	List<Event> findAllEvents();

	public List<Event> getEventList(int start, int offSet);

	public Long getEventListCount();

	public void updateEvent(Event event);

	public EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class entityClass);

}
