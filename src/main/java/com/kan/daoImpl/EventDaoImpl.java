package com.kan.daoImpl;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.swing.SortOrder;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.kan.dao.AbstractDao;
import com.kan.dao.EventDao;
import com.kan.dto.EventDto;
import com.kan.entity.Event;

@Repository
@Transactional
public class EventDaoImpl extends AbstractDao<Long, Event> implements EventDao {

	@Override
	public void deleteEvet(Event event) {
		getEntityManager().remove(event);
	}

	@Override
	public Event findOne(Long id) {

		@SuppressWarnings("unchecked")
		List<Event> eventList = (List<Event>) getEntityManager().createQuery("select u from Event u where u.id = :id")
				.setParameter("id", id).getResultList();

		if (1 <= eventList.size()) {
			return eventList.get(0);
		}

		return null;
	}

	@Override
	public Event saveEvent(Event event) {
		try {
			getEntityManager().persist(event);
			return findOne(Long.valueOf(String.valueOf(event.getId())));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> findAllEvents() {
		List<Event> events = (List<Event>) getEntityManager().createQuery("select u from Event u").getResultList();
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getEventList(int start, int offSet) {
		List<Event> eventList = (List<Event>) getEntityManager().createQuery("select d from Event d")
				.setFirstResult(start).setMaxResults(offSet).getResultList();
		return eventList;
	}

	@Override
	public Long getEventListCount() {
		Long count = (Long) getEntityManager().createQuery("select count(d.id) from vent d").getSingleResult();
		return count;
	}

	@Override
	public void updateEvent(Event event) {
		try {
			getEntityManager().merge(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public EventDto loadEvent(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters, Class entityClass) {

		try {

			Query query = load(sortField, sortOrder, filters, entityClass);
			Long count = (long) query.getResultList().size();// ONCE BUNU
																// YAPMASSAN
																// SONRA
																// SETFIRSTRESULT
																// ILE QUERY
																// DEGISIYOR

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
