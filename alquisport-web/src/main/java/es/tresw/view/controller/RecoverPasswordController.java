package es.tresw.view.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;

import org.apache.log4j.Logger;

import es.tresw.db.entities.User;
import es.tresw.service.RegisterService;
import es.tresw.util.Messages;

public class RecoverPasswordController implements Serializable{
	
	private static final Logger log = Logger.getLogger(RecoverPasswordController.class);
	
	@NotNull
	private String username;
	@NotNull
	private String email;
	
	
	/*SERVICE A UTILIZAR*/
	@ManagedProperty("#{registerService}")  
	private RegisterService registerService;
	
	
	public String recoverPassword()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		User u = registerService.getUserByUsername(username);
		
		String msg=validate(u);
		if(msg==null)
		{
			//Si es null quiere decir que no hay errores, por lo que cambiamos el password
			registerService.recoverPassword(u);
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,Messages.getString("recoverpassword_ok_sumary"),Messages.getString("recoverpassword_ok_detail"));
			facesContext.addMessage("recover_password",message);
		}else
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,Messages.getString(msg),Messages.getString(msg));
			facesContext.addMessage("recover_password",message);
		}
		return null;
	}
	
	/**
	 * metodo que va a validar si se puede realizar la operacion correspondiente. 
	 * Tendra que buscar el usuario en BBDD y mirar si el correo indicado por el usuario es el mismo al registrado
	 * @return
	 */
	private String validate(User u)
	{
		String msg = null;
		//leemos el usuario
		
		if(u==null)
			msg = "recoverpassword_user_noexist";
		else if(!u.getContactInfo().getEmail().equals(email))
			msg = "recoverpassword_email_distint";
		
		return msg;
	}

	/*GETTERS AND SETTERS*/

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
