package es.tresw.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_AdministratorDao;
import es.tresw.db.dao.I_ClientDao;
import es.tresw.db.dao.I_UserDao;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.User;
import es.tresw.view.controller.RecoverPasswordController;

@Transactional
public class RegisterService {
	
	private static final Logger log = Logger.getLogger(RegisterService.class);
	
	/*DAOs necesarios*/
	@Autowired
	private I_ClientDao clientDao;
	
	@Autowired
	private I_AdministratorDao administratorDao;
	
	@Autowired
	private I_UserDao userDao;
	
	@Autowired
	private NotificationService notificacionService;
	
	public boolean register(Client c)
	{
		//Encriptamos la contraseña en MD5
		PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword(c.getPassword(), null);
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
	    String hashedPass = encoder.encodePassword(a.getPassword(), null);
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
	
	/**
	 * Cambia el password del usuario indicado asignandole uno aleatorio y envia un correo con la nueva
	 * @param user
	 * @return
	 */
	public void recoverPassword(User user)
	{
		boolean resultado;
		//Creamos la nueva clave
		String newPassword = getPassword(10);
		
		//La codificamos en md5
		PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword(newPassword, null);
	    
	    //guardamos el cambio en bbdd
	    user.setPassword(hashedPass);
	    userDao.update(user);
	    
	    log.info("");
	    
	    //Enviamos el mail
	    notificacionService.recoverPassword(user.getUsername(), newPassword);
	}
	
	public User getUserByUsername(String username)
	{
		return userDao.findByName(username);
	}
	
	/*PRIVATE METHOD*/
	/**
	 * Crea una cadena formada por numeros, mayusculas y minusculas de longitud indicada por parametro
	 * @param length
	 * @return
	 */
	public static String getPassword(int length) {
		String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
		String pswd = "";
 
		for (int i = 0; i < length; i++) {
			pswd+=(base.charAt((int)(Math.random() * base.length())));
		}
 
		return pswd;
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

	public I_UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(I_UserDao userDao) {
		this.userDao = userDao;
	}

	public NotificationService getNotificacionService() {
		return notificacionService;
	}

	public void setNotificacionService(NotificationService notificacionService) {
		this.notificacionService = notificacionService;
	}

}
