package com.kan.daoImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.swing.SortOrder;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.kan.dao.AbstractDao;
import com.kan.dao.DonorDao;
import com.kan.dto.DonorDto;
import com.kan.entity.Donor;

@Repository
@Transactional
public class DonorDaoImpl extends AbstractDao<Long, Donor> implements DonorDao {

	@Override
	public Donor findOne(Long donorId) {
		@SuppressWarnings("unchecked")
		List<Donor> donorList = (List<Donor>) getEntityManager().createQuery("select u from Donor u where u.id = :id")
				.setParameter("id", donorId).getResultList();

		if (1 <= donorList.size()) {
			return donorList.get(0);
		}

		return null;
	}

	@Override
	public void saveEvent(Donor donor) {
		try {
			getEntityManager().persist(donor);
			findOne(Long.valueOf(String.valueOf(donor.getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteDonor(Donor donor) {
		getEntityManager().remove(donor);
	}

	@Override
	public void updateEvent(Donor donor) {
		try {
			getEntityManager().merge(donor);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public DonorDto loadDonor(int start, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class<Donor> entityClass) {
		try {

			Query query = load(sortField, sortOrder, filters, entityClass);
			Long count = (long) query.getResultList().size();// ONCE BUNU
																// YAPMASSAN
																// SONRA
																// SETFIRSTRESULT
																// ILE QUERY
																// DEGISIYOR

			List<Donor> myDonorList = null;
			myDonorList = (List<Donor>) query.setFirstResult(start).setMaxResults(pageSize).getResultList();

			DonorDto donorDto = new DonorDto();
			donorDto.setResultSet(myDonorList);
			donorDto.setTotalRecords(count);

			return donorDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Long getDonorListCount() {
		// TODO Auto-generated method stub
		return null;
	}

}
