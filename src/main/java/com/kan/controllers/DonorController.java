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

import com.kan.dto.DonorDto;
import com.kan.entity.Donor;
import com.kan.services.DonorService;
import com.kan.services.UserService;

@RestController
public class DonorController {
	@Autowired
	@Qualifier("donorService")
	DonorService donorService;

	@Autowired
	@Qualifier("userService")
	UserService userService;

	@RequestMapping(value = { "/getDonor/{id}" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<DonorDto> getDonor(@PathVariable("id") Long id) {

		Donor fetchedDonor = donorService.findOne(id);

		DonorDto donorDto = new DonorDto();
		List<Donor> resultSet = new ArrayList<Donor>();
		resultSet.add(fetchedDonor);
		donorDto.setResultSet(resultSet);
		donorDto.setTotalRecords(Long.valueOf(resultSet.size()));

		return new ResponseEntity<DonorDto>(donorDto, HttpStatus.OK);
	}

	@RequestMapping(value = { "/saveDonor" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Donor> create(@RequestBody Donor donor) {
//		@RequestMapping(value = { "/saveDonor/{userId}" }, method = RequestMethod.POST, produces = "application/json")
//		public ResponseEntity<Donor> create(@PathVariable("userId") Long userId, @RequestBody Donor donor) {

		try {
			donor.setCreatedAt(new Date());
			donor.setCreatedBy(donor.getCreatedBy());
			// donor.setUserId(userId);
			donorService.saveDonor(donor);
		} catch (Exception ex) {
			return null;
		}

		return new ResponseEntity<Donor>(donor, HttpStatus.OK);
	}

	@RequestMapping(value = { "/deleteDonor" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Donor> delete(@RequestBody List<Long> donorList) {

		List<Donor> willBeRemovedDonor = new ArrayList<Donor>();
		Donor fetchedDonor = null;

		for (Long donorId : donorList) {
			fetchedDonor = donorService.findOne(donorId);
			willBeRemovedDonor.add(fetchedDonor);
		}

		if (fetchedDonor == null) {
			return new ResponseEntity<Donor>(HttpStatus.NOT_FOUND);
		}

		try {
			for (Donor donor : willBeRemovedDonor) {
				donorService.deleteDonor(donor);
			}
		} catch (Exception ex) {
			return new ResponseEntity<Donor>(HttpStatus.EXPECTATION_FAILED);
		}

		return new ResponseEntity<Donor>(HttpStatus.OK);
	}

	@RequestMapping(value = { "/updateDonor" }, method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Donor> updateDonor(@RequestBody Donor donor) {
		try {
			donorService.updateDonor(donor);
		} catch (Exception ex) {
			return null;
		}

		return new ResponseEntity<Donor>(donor, HttpStatus.OK);
	}

	@RequestMapping(value = { "/getDonors" }, method = RequestMethod.POST)
	public ResponseEntity<DonorDto> getDonorList(@RequestBody SearchQuery searchQuery) {

		DonorDto donorDto = donorService.loadDonor(searchQuery.getStart(), searchQuery.getOffset(), null, null,
				searchQuery.getFilterMap(), Donor.class);

		return new ResponseEntity<DonorDto>(donorDto, HttpStatus.OK);
	}

	@RequestMapping(value = { "/getDonorsCount" })
	public ResponseEntity<Long> getDonorCount() {

		Long userCount = donorService.getDonorListCount();
		return new ResponseEntity<Long>(userCount, HttpStatus.OK);
	}

}
