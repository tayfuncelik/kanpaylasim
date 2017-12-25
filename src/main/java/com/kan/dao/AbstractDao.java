package com.kan.dao;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.swing.SortOrder;
 
public abstract class AbstractDao<PK extends Serializable, T> {
     
    private final Class<T> persistentClass;
     
    @SuppressWarnings("unchecked")
    public AbstractDao(){
        this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }
     
    @PersistenceContext
    EntityManager entityManager;
     
    protected EntityManager getEntityManager(){
        return this.entityManager;
    }
 
    protected T getByKey(PK key) {
        return (T) entityManager.find(persistentClass, key);
    }
 
    protected void persist(T entity) {
        entityManager.persist(entity);
    }
     
    protected void update(T entity) {
        entityManager.merge(entity);
    }
 
    protected void delete(T entity) {
        entityManager.remove(entity);
    }
    
    
    protected Query load(String sortField, SortOrder sortOrder, Map<String, Object> filters, Class entityClass) {
    	 
    		List<Object> list = new ArrayList<Object>();
    		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
    		CriteriaQuery<Object> cq = cb.createQuery(entityClass);
    		Root<Object> from = cq.from(entityClass);
    		if (sortField != null) {
    			if (sortOrder == SortOrder.ASCENDING) {
    				cq.orderBy(cb.asc(from.get(sortField)));
    			} else {
    				cq.orderBy(cb.desc(from.get(sortField)));
    			}
    		}

    		if (!filters.isEmpty()) {
    			
    			List<Predicate> predicates= convertQueryMapIntoPredicateList(filters, from, cb);
    			cq.where(predicates.toArray(new Predicate[predicates.size()]));
    		}
    		
    		Query q = getEntityManager().createQuery(cq);
    		
    		return q;
    		
    	}

	private List<Predicate> convertQueryMapIntoPredicateList(Map<String, Object> incomemap, Root<?> from,
    			CriteriaBuilder builder) {
    		List<Predicate> predicates = new ArrayList<Predicate>();
    		for (Map.Entry<String, Object> map : incomemap.entrySet()) {

    			if ((map.getKey() != null) && (map.getValue() != null)) {
    				
    				predicates.add(builder.equal(from.get(map.getKey()), map.getValue()));

//    				ArrayList<Object> myList = (ArrayList<Object>) map.getValue();
//    				for (Object test : myList) {
//
//    					if (test.toString().contains(";")) {
//    						predicates.add(builder.like(from.get(map.getKey()), "%" + test + "%"));
//    					} else {
//    						predicates.add(builder.equal(from.get(map.getKey()), test));
//    					}
//    				}
    			}
    		}
    		return predicates;
    	}
 
}