package es.tresw.db.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_AdministratorDao;
import es.tresw.db.entities.Administrator;

@Repository("administratorDao")
public class AdministratorDao extends GenericDao<Administrator, Long> implements  I_AdministratorDao 
{

	public Administrator findByName(String username)
	{
    	Criteria criteria = getSession().createCriteria(Administrator.class);
    	criteria.add(Restrictions.eq("username", username));
    	return (Administrator)criteria.uniqueResult();
    } 


}