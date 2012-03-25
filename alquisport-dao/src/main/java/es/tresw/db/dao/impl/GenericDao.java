package es.tresw.db.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import es.tresw.db.constants.PisteaConstants;
import es.tresw.db.dao.I_GenericDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



/**
 * Hibernate implementation of GenericDao A typesafe implementation of CRUD.
 */

public class GenericDao<T, PK extends Serializable> implements I_GenericDao<T, PK> {
	protected SessionFactory sessionFactory;
	private Class<T> type;

	@SuppressWarnings("unchecked")
	public GenericDao()
	{
		 this.type = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	} 

	@SuppressWarnings("unchecked")
	//@Transactional(readOnly=false)
	public PK create(T object) 
	{
		return (PK) getSession().save(object);
	}

	@SuppressWarnings("unchecked")
	//@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public T read(PK id) 
	{
		return (T) getSession().get(type, id);
	}

	//@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<T> readAll() 
	{
		return readByCriteria();
	}

	@SuppressWarnings("unchecked")
	//@Transactional(readOnly=true, isolation=Isolation.READ_COMMITTED)
	public List<T> readByCriteria(Criterion... criterion) 
	{
		Criteria crit = getSession().createCriteria(type);
		for (Criterion c : criterion)
		{
			crit.add(c);
		}
		return crit.list();
	}

	public boolean exists(List<String> fields, List<String> expressions, List<String> values, List<String> types) 
	{
		List<T> result = readByField(fields, expressions, values, types);
		if(result==null || result.size()==0)
			return false;
		else
			return true;
					
	}
	
	public boolean existsStringField(String field, String value)
	{
		List<String> fields = new ArrayList<String>();
		fields.add(field); 
		List<String> expressions = new ArrayList<String>();
		expressions.add(PisteaConstants.EQUALS);
		List<String> values = new ArrayList<String>();
		values.add(value);
		List<String> types = new ArrayList<String>();
		types.add("String");
		return exists(fields, expressions, values, types);
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
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
    public List<T> readByField(List<String> fields, List<String> expressions, List<String> values, List<String> types)
    {
        Criteria filter = getSession().createCriteria(type);
        int i = 0;
        boolean isInt=false;
        for (String field : fields)
        {   
        	String expression = expressions.get(i);
        	String value = values.get(i);
            if(types.get(i).equals(PisteaConstants.INT))
            {
                isInt=true;
            }
            if (expression.equals(PisteaConstants.EQUALS))
            {
                if(isInt)
                    filter.add(Restrictions.eq(field, Integer.valueOf(value)));
                else
                    filter.add(Restrictions.eq(field, value));
            }
            else if (expression.equals(PisteaConstants.LESS_THAN))
            {
                if(isInt)
                    filter.add(Restrictions.le(field, Integer.valueOf(value)));
                else
                    filter.add(Restrictions.le(field, value));
            }
            else if (expression.equals(PisteaConstants.LARGER_THAN))
            {
                if(isInt)
                    filter.add(Restrictions.lt(field, Integer.valueOf(value)));
                else
                    filter.add(Restrictions.lt(field, field));
            }
            else if (expression.equals(PisteaConstants.CONTAINS))
            {
                    filter.add(Restrictions.ilike(field, value, MatchMode.ANYWHERE ));
            }
            i++;
        }
        return filter.list();
    }
	

	
	@Autowired  
	public void setSessionFactory(SessionFactory sessionFactory) 
	{
		//System.out.println(this.getClass()+"sesion en el setSessionFactory="+sessionFactory.getCurrentSession());
		this.sessionFactory = sessionFactory;
	}
}