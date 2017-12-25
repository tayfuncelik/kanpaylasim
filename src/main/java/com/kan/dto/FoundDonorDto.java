package com.kan.dto;

import com.kan.entity.Event;

public class FoundDonorDto {
	private Event event;
	private DonorDto donorDto;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public DonorDto getDonorDto() {
		return donorDto;
	}

	public void setDonorDto(DonorDto donorDto) {
		this.donorDto = donorDto;
	}

}
