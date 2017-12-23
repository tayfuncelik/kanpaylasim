package com.kan.serviceImpl;

import java.util.Map;

import javax.swing.SortOrder;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.kan.dao.MatchDao;
import com.kan.dto.EventDto;
import com.kan.entity.Donor;
import com.kan.services.MatchService;

@Component
@Qualifier("matchService")
@Repository
@Transactional
public class MatchServiceImpl implements MatchService {

	@Autowired
	private MatchDao matchDao;

	@SuppressWarnings("rawtypes")
	@Override
	public EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class entityClass) {
		return matchDao.loadEvent(first, pageSize, sortField, sortOrder, filters, entityClass);
	}

	@Override
	public Donor findOne(Long id) {
		return null;
	}

}
