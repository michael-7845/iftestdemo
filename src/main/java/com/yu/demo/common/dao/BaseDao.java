/**
 * 
 */
package com.yu.demo.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @author yukemin
 */
public interface BaseDao<T> {
	//Get entity by id
	T get(Class<T> entityClazz, Serializable id);
	//Save
	Serializable save(T entity);
	//Update
	void update(T entity);
	//Delete
	void delete(T entity);
	//Delete by id
	void delete(Class<T> entityClazz, Serializable id);
	//Get all
	List<T> findAll(Class<T> entityClazz);
	//Get amount
	long findCount(Class<T> entityClazz);
}
