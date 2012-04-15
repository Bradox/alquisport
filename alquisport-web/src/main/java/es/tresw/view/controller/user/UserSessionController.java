package es.tresw.view.controller.user;

import java.io.Serializable;

import javax.faces.bean.ManagedProperty;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.AdministratorSportFacility;
import es.tresw.db.entities.User;
import es.tresw.service.RegisterService;

public class UserSessionController implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#registerService")
	RegisterService registerService;
	
	private User user;
	
	
	public UserSessionController()
	{
	}
	
	
	public User getUser() {
		if(user==null)
		{
		
			// Do nothing with the session because Spring give us a better way to retrieve the principal
			Object userActual = ((SecurityContext)SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
			
			if(userActual instanceof String)
			{
				//Sera un usuario anonimo
				return null;
			}
			else if(userActual instanceof org.springframework.security.core.userdetails.User)
			{
				org.springframework.security.core.userdetails.User springUser = (org.springframework.security.core.userdetails.User)userActual;
				user = registerService.getUserByusername(springUser.getUsername());
				return user;
			}else
				return null;
		}else
			return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public RegisterService getRegisterService() {
		return registerService;
	}


	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}
	
	public boolean isLogado()
	{
		if(getUser()!=null)
			return true;
		else
			return false;
	}
	
	public Administrator getAdministrator()
	{
		if(user instanceof Administrator)
			return (Administrator)user;
		else
			return null;
	}
	
	public void createAdministrators(AdministratorSportFacility asf)
	{
		registerService.createAdministrators(asf);
	}
	

}
