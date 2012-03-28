package es.tresw.db.dao;

import es.tresw.db.entities.Administrator;


public interface I_AdministratorDao extends I_GenericDao<Administrator, Long> 
{

	public Administrator findByName(String username); 
	
}