package es.tresw.db.dao.impl;

import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import es.tresw.db.dao.GenericDao;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import java.lang.reflect.ParameterizedType;


/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */
public class GenericDaoImpl<T, PK extends Serializable> implements GenericDao<T, PK> {
	private SessionFactory sessionFactory;
	private Class<T> type;

	/**
	 * @param type
	 */
	public GenericDaoImpl() {
		this.type =  (Class<T>)((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.socialnetwork.db.dao#create(java.lang.Object)
	 */
	public PK create(T object) {
		return (PK) getSession().save(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.socialnetwork.db.dao#read(java.io.Serializable)
	 */
	public T read(PK id) {
		return (T) getSession().get(type, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.socialnetwork.db.dao#readAll()
	 */
	public List<T> readAll() {
		return readByCriteria();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.socialnetwork.db.dao#readByCriteria(org.hibernate.criterion.Criterion[])
	 */
	public List<T> readByCriteria(Criterion... criterion) {
		Criteria crit = getSession().createCriteria(type);
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return crit.list();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.socialnetwork.db.dao#update(java.lang.Object)
	 */
	public void update(T object) {
		getSession().update(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.socialnetwork.db.dao#delete(java.lang.Object)
	 */
	public void delete(T object) {
		getSession().delete(object);
	}

	/**
	 * @return
	 */
	public Session getSession() {
		boolean allowCreate = true;
		return SessionFactoryUtils.getSession(sessionFactory, allowCreate);
	}

	/**
	 * Injection via Spring
	 * 
	 * @param sessionFactory
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}