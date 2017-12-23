//package com.kan.daoImpl;
//
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import javax.persistence.Query;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Expression;
//import javax.persistence.criteria.Predicate;
//import javax.persistence.criteria.Root;
//import javax.swing.SortOrder;
//import javax.transaction.Transactional;
//
//import org.springframework.stereotype.Component;
//import org.springframework.stereotype.Repository;
//
//import com.kan.dao.AbstractDao;
//import com.kan.entity.BaseEntity;
//import com.kan.entity.Data;
//
//@Repository
//@Transactional
////@Component
//public class DENEME extends AbstractDao<Long, Data>{
//
//
//
//	public List<Object> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters, Class entityClass) {
//		// TODO Auto-generated method stub
//		try {
//			List<Object> list = new ArrayList<Object>();
//
//			// Criteria Query
//			CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
//			CriteriaQuery<Object> cq = cb.createQuery(entityClass);
//
//			// From
//			Root<Object> from = cq.from(entityClass);
//
//			// Sort
//			if (sortField != null) {
//				if (sortOrder == SortOrder.ASCENDING) {
//					cq.orderBy(cb.asc(from.get(sortField)));
//				} else {
//					cq.orderBy(cb.desc(from.get(sortField)));
//				}
//			}
//
//			// Filters
//			if (!filters.isEmpty()) {
//
//				List<Predicate> predicates = new ArrayList<Predicate>();
//
//				for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
//
//					String filterProperty = it.next();
//					Object filterValue = filters.get(filterProperty);
//					Expression<String> literal = cb.upper(cb.literal((String) filterProperty.toUpperCase()));
//
//					predicates.add(cb.like(cb.upper(from.get(filterProperty).as(String.class)), String.valueOf("%" + filterValue + "%").toUpperCase()));
//
//				}
//				cq.where(predicates.toArray(new Predicate[predicates.size()]));
//			}
//
//			Query q = getEntityManager().createQuery(cq);
//
//			list = q.setFirstResult(first).setMaxResults(pageSize).getResultList();
////			Root<entityClass> root = query.from(entityClass.class);
////			this.setRowCount(cb.count());
//			Object lala  = getEntityManager().createQuery(cq).getSingleResult();
//			
////			this.setRowCount(getEntityManager().createQuery(cq).getSingleResult());
//			return list;
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
//	}
//}
