package es.tresw.db.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

public interface I_GenericDao<T, PK extends Serializable>
{
	/**
	 * Create a new instance
	 * 
	 * @param newInstance
	 * @return
	 */
	PK create(T newInstance);

	/**
	 * Read by technical identifier
	 * 
	 * @param id
	 * @return
	 */
	T read(PK id);

	/**
	 * Read all items
	 * 
	 * @return
	 */
	List<T> readAll();

	/**
	 * Read items by criteria
	 * 
	 * @param criterion
	 * @return
	 */
	List<T> readByCriteria(Criterion... criterion);
	
	/**
	 * Check if exists a object with field = value
	 * @param field
	 * @param value
	 * @return
	 */
	boolean exists(String field, String value);

	/**
	 * Update data of transient object
	 * 
	 * @param transientObject
	 */
	void update(T transientObject);

	/**
	 * Delete persistent object
	 * 
	 * @param persistentObject
	 */
	void delete(T persistentObject);
	
	Session getSession();
	
}