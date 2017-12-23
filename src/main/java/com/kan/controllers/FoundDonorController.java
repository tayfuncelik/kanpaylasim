package com.kan.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kan.dto.DonorDto;
import com.kan.dto.EventDto;
import com.kan.dto.FoundDonorDto;
import com.kan.dto.MatchedDto;
import com.kan.entity.Donor;
import com.kan.entity.Event;
import com.kan.services.DonorService;
import com.kan.services.EventService;
import com.kan.services.MatchService;

@RestController
public class FoundDonorController {
	
	@Autowired
	@Qualifier("donorService")
	DonorService donorService;

	@Autowired
	@Qualifier("matchService")
	MatchService matchService;
	
	@Autowired
	@Qualifier("eventService")
	EventService eventService;
/*
 * 
 * userId den en son eventi çekiyoruz.
 * o eventdeki kan grubuna göre donor arıyoruz
 */
	@RequestMapping(value = { "/foundDonors/{userId}" }, method = RequestMethod.POST)
	public FoundDonorDto getDonorList(@PathVariable("userId") Long userId,@RequestBody SearchQuery secondSearchQuery) {

		Map<String, Object> filterMap = new HashMap<String, Object>();
//		filterMap.put("userId",userId);
		filterMap.put("createdBy",userId);

		SearchQuery searchQuery = new SearchQuery();
		searchQuery.setStart(0);
		searchQuery.setOffset(1);
		searchQuery.setFilterMap(filterMap);
		
		

		EventDto eventDto = eventService.loadEvent(secondSearchQuery.getStart(), secondSearchQuery.getOffset(), "Id", SortOrder.DESCENDING,
				secondSearchQuery.getFilterMap(), Event.class);
		
		Event lastEventRecord = eventDto.getResultSet().size()>0?eventDto.getResultSet().get(0):null;
 
		
		filterMap = new HashMap<String, Object>();
		filterMap.put("bloodType",lastEventRecord.getBloodType().getBloodType());
		secondSearchQuery.setFilterMap(filterMap);
		
		
		DonorDto donorDto = donorService.loadDonor(secondSearchQuery.getStart(), secondSearchQuery.getOffset(), null, null,
				secondSearchQuery.getFilterMap(), Donor.class);

		FoundDonorDto foundDonorDto = new FoundDonorDto();
		foundDonorDto.setEvent(lastEventRecord);
		foundDonorDto.setDonorDto(donorDto);

		return  foundDonorDto;
	}
	
	
}
