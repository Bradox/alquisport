package es.tresw.db.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ClientDao;
import es.tresw.db.entities.Client;

@Repository("clientDao")
public class ClientDao extends GenericDao<Client, Long> implements  I_ClientDao 
{

	public Client findByName(String username)
	{
    	Criteria criteria = getSession().createCriteria(Client.class);
    	criteria.add(Restrictions.eq("username", username));
    	return (Client)criteria.uniqueResult();
    } 

}