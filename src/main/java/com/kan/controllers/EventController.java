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

import com.kan.dto.EventDto;
import com.kan.entity.BloodType;
import com.kan.entity.Event;
import com.kan.services.EventService;

@RestController
public class EventController {

	@Autowired
	@Qualifier("eventService")
	EventService eventService;

	@RequestMapping(value = { "/getEvent/{id}" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<EventDto> getEvent(@PathVariable("id") Long id) {

		Event fetchedEvent = eventService.findOne(id);

		EventDto eventDto = new EventDto();
		List<Event> resultSet = new ArrayList<Event>();
		resultSet.add(fetchedEvent);
		eventDto.setResultSet(resultSet);
		eventDto.setTotalRecords(Long.valueOf(resultSet.size()));

		return new ResponseEntity<EventDto>(eventDto, HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveEvent" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Event> create(@RequestBody Event event) {

		try {
			event.setCreatedAt(new Date());
			eventService.saveEvent(event);
		} catch (Exception ex) {
			return null;
		}

		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}
   
	@RequestMapping(value = { "/deleteEvent" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Event> delete(@RequestBody List<Long> eventList) {

		List<Event> willBeRemovedEvent = new ArrayList<Event>();
		Event fetchedEvent = null;

		for (Long ev : eventList) {
			fetchedEvent = eventService.findOne(ev);
			willBeRemovedEvent.add(fetchedEvent);
		}

		if (fetchedEvent == null) {
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		}

		try {
			for (Event evnt : willBeRemovedEvent) {
				eventService.deleteEvet(evnt);
			}
		} catch (Exception ex) {
			return new ResponseEntity<Event>(HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Event>(HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateEvent" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Event> updateEvent(@RequestBody Event event) {
		try {
			eventService.updateEvent(event);
		} catch (Exception ex) {
			return null;
		}

		return new ResponseEntity<Event>(event, HttpStatus.OK);
	}

	@RequestMapping(value = { "/getEvents" }, method = RequestMethod.POST)
	public ResponseEntity<EventDto> getEventList(@RequestBody SearchQuery searchQuery) {

		EventDto eventDto = eventService.loadEvent(searchQuery.getStart(), searchQuery.getOffset(), null, null,
				searchQuery.getFilterMap(), Event.class);

		return new ResponseEntity<EventDto>(eventDto , HttpStatus.OK);
	}

	@RequestMapping(value = { "/getEventsCount" })
	public ResponseEntity<Long> getEventCount() {

		Long userCount = eventService.getEventListCount();
		return new ResponseEntity<Long>(userCount, HttpStatus.OK);
	}
	
	

	@RequestMapping(value = { "/bloodTypes" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<List<BloodTypeDto>> getbloodTypes() {

		List<BloodTypeDto> bloodTypes = new ArrayList<BloodTypeDto>();

		BloodTypeDto bloodTypesDto = null;

		for (BloodType bt : BloodType.values()) {
			bloodTypesDto = new BloodTypeDto();
			bloodTypesDto.setValue(bt.getBloodType());
			bloodTypesDto.setName(bt.name());
			bloodTypes.add(bloodTypesDto);
		}

		return new ResponseEntity<List<BloodTypeDto>>(bloodTypes, HttpStatus.OK);
	}
	
	class BloodTypeDto {

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