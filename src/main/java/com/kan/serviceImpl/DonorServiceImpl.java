package com.kan.serviceImpl;

import java.util.Map;

import javax.swing.SortOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.kan.dao.DonorDao;
import com.kan.dto.DonorDto;
import com.kan.entity.Donor;
import com.kan.services.DonorService;

@Component
@Qualifier("donorService")
public class DonorServiceImpl implements DonorService {

	@Autowired
	private DonorDao donorDao;

	@Override
	public Donor findOne(Long donorId) {
		return donorDao.findOne(donorId);
	}

	@Override
	public void saveDonor(Donor donor) {
		donorDao.saveEvent(donor);
	}

	@Override
	public void deleteDonor(Donor donor) {
		donorDao.deleteDonor(donor);
	}

	@Override
	public void updateDonor(Donor donor) {
		donorDao.updateEvent(donor);
	}

	@Override
	public DonorDto loadDonor(int start, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class<Donor> entityClass) {
		return donorDao.loadDonor(start, pageSize, sortField, sortOrder, filters, entityClass);
	}

	@Override
	public Long getDonorListCount() {
		return donorDao.getDonorListCount();
	}

}
