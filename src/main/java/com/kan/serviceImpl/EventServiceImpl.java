package com.kan.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kan.dao.EventDao;
import com.kan.dto.EventDto;
import com.kan.entity.Event;
import com.kan.services.EventService;

@Component
@Qualifier("eventService")
public class EventServiceImpl implements EventService {
	
	@Autowired
	private EventDao eventDao;

	@Override
	public void deleteEvet(Event event) {
		eventDao.deleteEvet(event);
	} 

	@Override
	public Event findOne(Long id) {
		return eventDao.findOne(id);
	}

	@Override
	public Event saveEvent(Event event) {
		return eventDao.saveEvent(event);
	}

	@Override
	public List<Event> findAllEvents() {
		return eventDao.findAllEvents();
	}

	@Override
	public List<Event> getEventList(int start, int offSet) {
		return eventDao.getEventList(start,offSet);
	}


	@Override
	public Long getEventListCount() {
		return eventDao.getEventListCount();
	}

	@Override
	public void updateEvent(Event event) {
		 eventDao.updateEvent(event);
	}


	@Override
	public EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class entityClass) {
		return eventDao.loadEvent(first, pageSize, sortField, sortOrder, filters, entityClass);
	}

}
