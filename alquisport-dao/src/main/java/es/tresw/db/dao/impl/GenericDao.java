package es.tresw.db.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;

import es.tresw.db.dao.I_GenericDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */

public class GenericDao<T, PK extends Serializable> implements I_GenericDao<T, PK> {
	private SessionFactory sessionFactory;
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDao()
	{
		 this.type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	} 

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=false)
	public PK create(T object) 
	{
		return (PK) getSession().save(object);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public T read(PK id) 
	{
		return (T) getSession().get(type, id);
	}

	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<T> readAll() 
	{
		return readByCriteria();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<T> readByCriteria(Criterion... criterion) 
	{
		Criteria crit = getSession().createCriteria(type);
		for (Criterion c : criterion)
		{
			crit.add(c);
		}
		return crit.list();
	}

	@Transactional(readOnly=false)
	public void update(T object) 
	{
		getSession().update(object);
	}

	@Transactional(readOnly=false)
	public void delete(T object) 
	{
		getSession().delete(object);
	}

	public Session getSession() 
	{
		//boolean allowCreate = true;
		//return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
		return sessionFactory.getCurrentSession();
	}

	@Autowired  
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
}