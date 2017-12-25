package com.kan.dto;

import com.kan.entity.Donor;

public class MatchedDto {

	private Donor donor;
	private EventDto EventDto;

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	public EventDto getEventDto() {
		return EventDto;
	}

	public void setEventDto(EventDto eventDto) {
		EventDto = eventDto;
	}

}
