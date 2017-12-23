package com.kan.services;

import java.util.Map;

import javax.swing.SortOrder;

import com.kan.dto.DonorDto;
import com.kan.entity.Donor;

public interface DonorService {

	Donor findOne(Long donorId);

	void saveDonor(Donor donor);

	void deleteDonor(Donor donor);

	void updateDonor(Donor donor);

	DonorDto loadDonor(int start, int offset, String object, SortOrder SortOrder, Map<String, Object> filterMap,
			Class<Donor> class1);

	Long getDonorListCount();

}
