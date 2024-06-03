package vn.dev.cinema.service;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import vn.dev.cinema.entity.BaseEntity;


@Service
public abstract class BaseService<E extends BaseEntity> {
	
	@PersistenceContext
	EntityManager entityManager;
	
	public abstract Class<E> clazz();
	
	public E getById(Integer id) {
		return entityManager.find(clazz(), id);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> findAll() {
		Table table = clazz().getAnnotation(Table.class);
		return (List<E>) entityManager.createNativeQuery("SELECT * FROM " + table.name(), clazz()).getResultList();
	}

	@Transactional
	public E saveOrUpdate(E entity) {
		if (entity.getId() == null || entity.getId() <= 0) { //Add
			entityManager.persist(entity);
			return entity;
		}
		else { //update
			return entityManager.merge(entity);
		}
	}
	
	
	public void delete(E entity) {
		entityManager.remove(entity);
		
	}
	
	public void deleteById(Integer id) {
		E entity = this.getById(id);
		delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql) {
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<E>();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<E> executeNativeSql(String sql, Integer page, Integer sizeOfPage) { // Co phan trang
		try {
			Query query = entityManager.createNativeQuery(sql, clazz());
			query.setFirstResult((page - 1) * sizeOfPage); // vị trí bản ghi đầu trang
			query.setMaxResults(sizeOfPage); // so ban ghi tren mot trang
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<E>();
		}
	}
	
	/*
	 * 
	 * getEntityByNativeSql
	 * 
	 * 
	 * */
	public E getEntityByNativeSql(String sql) {
		try {
			return this.executeNativeSql(sql).get(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

}
