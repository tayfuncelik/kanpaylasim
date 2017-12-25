package com.kan.daoImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.swing.SortOrder;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.kan.dao.AbstractDao;
import com.kan.dao.MatchDao;
import com.kan.dto.EventDto;
import com.kan.entity.Event;

@Repository
@Transactional
public class MatchDaoImpl extends AbstractDao<Long, Event> implements MatchDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class entityClass) {
		try {

			Query query = load(sortField, sortOrder, filters, entityClass);
			Long count = (long) query.getResultList().size();
			List<Event> myEventList = null;
			myEventList = (List<Event>) query.setFirstResult(first).setMaxResults(pageSize).getResultList();

			EventDto eventDto = new EventDto();
			eventDto.setResultSet(myEventList);
			eventDto.setTotalRecords(count);

			return eventDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
