package es.tresw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_AdministratorDao;
import es.tresw.db.dao.I_ClientDao;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Client;

@Transactional
public class RegisterService {
	
	/*DAOs necesarios*/
	@Autowired
	private I_ClientDao clientDao;
	
	@Autowired
	private I_AdministratorDao administratorDao;
	
	public boolean register(Client c)
	{
		//Encriptamos la contraseña en MD5
		PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword("koala", null);
	    c.setPassword(hashedPass);
		
		//Guardamos en BBDD el cliente
		Long id = (Long) clientDao.create(c);
		if(id!=null)
			return true;
		else
			return false;
	}
	
	public boolean register(Administrator a)
	{
		//Encriptamos la contraseña en MD5
		PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword("koala", null);
	    a.setPassword(hashedPass);
		
		//Guardamos en BBDD el cliente
		Long id = (Long) administratorDao.create(a);
		if(id!=null)
			return true;
		else
			return false;
	}
	
	public boolean existUser(String login)
	{
		return clientDao.existsStringField("login", login);
	}
	
	public boolean existEmail(String email)
	{
		return clientDao.existsStringField("contactInfo.email", email);
	}

	 
	/*GETTERS Y SETTERS*/
	public I_ClientDao getClientDao() {
		return clientDao;
	}

	public void setClientDao(I_ClientDao clientDao) {
		this.clientDao = clientDao;
	}

	public I_AdministratorDao getAdministratorDao() {
		return administratorDao;
	}

	public void setAdministratorDao(I_AdministratorDao administratorDao) {
		this.administratorDao = administratorDao;
	}
	
}
