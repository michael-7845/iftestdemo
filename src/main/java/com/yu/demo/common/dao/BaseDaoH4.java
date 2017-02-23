package com.yu.demo.common.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BaseDaoH4<T> implements BaseDao<T> {

	@Resource
    private SessionFactory sessionFactory;
	
	//Get entity by id
	@SuppressWarnings("unchecked")
	public T get(Class<T> entityClazz, Serializable id) {
		return (T)this.sessionFactory.getCurrentSession().get(entityClazz, id);
	}

	//Save
	public Serializable save(T entity) {
		return this.sessionFactory.getCurrentSession().save(entity);
	}

	//Update
	public void update(T entity) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(entity);
	}

	//Delete
	public void delete(T entity) {
		this.sessionFactory.getCurrentSession().delete(entity);
	}

	//Delete by id
	public void delete(Class<T> entityClazz, Serializable id) {
		this.sessionFactory.getCurrentSession()
		.createQuery("delete " + entityClazz.getSimpleName().toLowerCase() +
				" en where en.id = ?0")
		.setParameter("0", id)
		.executeUpdate();
	}

	//Get all
	public List<T> findAll(Class<T> entityClazz) {
		return find("select en from " + 
	        entityClazz.getSimpleName().toLowerCase() + " en");
	}

	//Get amount
	public long findCount(Class<T> entityClazz) {
		List<?> l = find("select count(*) from " +
	        entityClazz.getSimpleName().toLowerCase());
		if(l != null && l.size() == 1)
			return (Long)l.get(0);
		return 0;
	}

	@SuppressWarnings("unchecked")
	protected List<T> find(String hql) {
		return (List<T>)this.sessionFactory.getCurrentSession()
				.createQuery(hql).list();
	}
	
	@SuppressWarnings("unchecked")
	protected List<T> find(String hql, Object... params) {
		Query query = this.sessionFactory.getCurrentSession()
				.createQuery(hql);
		for(int i=0, len=params.length; i<len; i++) {
			query.setParameter(i+"", params[i]);
		}
		return (List<T>)query.list();
	}
}
