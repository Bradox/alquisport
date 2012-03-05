package es.tresw.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_AdministratorDao;
import es.tresw.db.dao.I_ClientDao;
import es.tresw.db.dao.I_RoleDao;
import es.tresw.db.dao.I_UserDao;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.Role;
import es.tresw.db.entities.User;

@Transactional
public class RegisterService {
	
	//ROLES DE USUARIO
	private static final Long ID_ROLE_SPORTFACILITY_ADMIN = new Long(3);
	private static final Long ID_ROLE_USER = new Long(2);
	
	private static final Logger log = Logger.getLogger(RegisterService.class);
	
	/*DAOs necesarios*/
	@Autowired
	private I_ClientDao clientDao;
	
	@Autowired
	private I_AdministratorDao administratorDao;
	
	@Autowired
	private I_UserDao userDao;
	
	@Autowired
	private I_RoleDao roleDao;
	
	@Autowired
	private NotificationService notificacionService;
	
	public boolean register(Client c)
	{
		//Encriptamos la contraseña en MD5
		PasswordEncoder encoder = new Md5PasswordEncoder();
	    String hashedPass = encoder.encodePassword(c.getPassword(), null);
	    c.setPassword(hashedPass);
	    
	    //Le asignamos el rol correspondiente
	    Role role = roleDao.read(ID_ROLE_USER);
	    List<Role> roles = new ArrayList<Role>();
	    roles.add(role);
	    c.setRoles(roles);
		
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
	    
	    //Le asignamos el rol correspondiente
	    Role role = roleDao.read(ID_ROLE_SPORTFACILITY_ADMIN);
	    a.getRoles().add(role);
		
		//Guardamos en BBDD el cliente
		Long id = (Long) administratorDao.create(a);
		if(id!=null)
			return true;
		else
			return false;
	}
	
	public List<Client> getClients()
	{
		return getClientDao().readAll();
	}
	
	public boolean existUser(String login)
	{
		return clientDao.existsStringField("username", login);
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
	    notificacionService.recoverPassword(user.getusername(), newPassword);
	}
	
	public User getUserByusername(String username)
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

	public I_RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(I_RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	
	

}
