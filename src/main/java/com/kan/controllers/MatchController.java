package com.kan.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kan.dto.DonorDto;
import com.kan.dto.EventDto;
import com.kan.dto.MatchedDto;
import com.kan.entity.Donor;
import com.kan.entity.Event;
import com.kan.services.DonorService;
import com.kan.services.EventService;
import com.kan.services.MatchService;

@RestController
public class MatchController {

	@Autowired
	@Qualifier("donorService")
	DonorService donorService;

	@Autowired
	@Qualifier("matchService")
	MatchService matchService;
	
	@Autowired
	@Qualifier("eventService")
	EventService eventService;


	@RequestMapping(value = { "/getMatch/{id}" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<DonorDto> getMatch(@PathVariable("id") Long id) {

		Donor fetchedDonor = matchService.findOne(id);

		DonorDto donorDto = new DonorDto();
		List<Donor> resultSet = new ArrayList<Donor>();
		resultSet.add(fetchedDonor);
		donorDto.setResultSet(resultSet);
		donorDto.setTotalRecords(Long.valueOf(resultSet.size()));

		return new ResponseEntity<DonorDto>(donorDto, HttpStatus.OK);
	}

	@RequestMapping(value = { "/getMatchs" }, method = RequestMethod.POST)
	public ResponseEntity<EventDto> getEventList(@RequestBody SearchQuery searchQuery) {

		EventDto eventDto = matchService.loadEvent(searchQuery.getStart(), searchQuery.getOffset(), null, null,
				searchQuery.getFilterMap(), Event.class);

		return new ResponseEntity<EventDto>(eventDto, HttpStatus.OK);
	}

	 
	@RequestMapping(value = { "/findFirstCreatedDonor/{userId}" }, method = RequestMethod.POST, produces = "application/json")
	public MatchedDto getFirstCreatedDonor(@PathVariable("userId") Long userId,@RequestBody SearchQuery secondSearchQuery) {
		
		Map<String, Object> filterMap = new HashMap<String, Object>();
//		filterMap.put("userId",userId);
		filterMap.put("createdBy",userId);

		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setStart(0);
		searchQuery.setOffset(1);
		searchQuery.setFilterMap(filterMap);
		
		DonorDto donorDto = donorService.loadDonor(searchQuery.getStart(), searchQuery.getOffset(), "Id", SortOrder.DESCENDING,
				searchQuery.getFilterMap(), Donor.class);
		
		Donor lastDonorRecord = donorDto.getResultSet().size()>0?donorDto.getResultSet().get(0):null;
		
		
		filterMap = new HashMap<String, Object>();
		filterMap.put("bloodType",lastDonorRecord.getBloodType().getBloodType());
		secondSearchQuery.setFilterMap(filterMap);
		
		
		EventDto eventDto = eventService.loadEvent(secondSearchQuery.getStart(), secondSearchQuery.getOffset(), null, null,
				secondSearchQuery.getFilterMap(), Event.class);

		MatchedDto matchedDto = new MatchedDto();
		matchedDto.setDonor(lastDonorRecord);
		matchedDto.setEventDto(eventDto);
		
		return matchedDto;
	}

}
