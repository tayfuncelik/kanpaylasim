package com.kan.dao;

import java.util.Map;

import javax.swing.SortOrder;

import com.kan.dto.DonorDto;
import com.kan.entity.Donor;

public interface DonorDao {

	public Donor findOne(Long donorId);

	public void saveEvent(Donor donor);

	public void deleteDonor(Donor donor);

	public void updateEvent(Donor donor);

	public DonorDto loadDonor(int start, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters,
			Class<Donor> entityClass);

	public Long getDonorListCount();

}
