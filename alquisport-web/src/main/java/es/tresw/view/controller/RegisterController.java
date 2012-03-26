package es.tresw.view.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Client;
import es.tresw.service.RegisterService;
import es.tresw.util.FacesUtil;
import es.tresw.util.Messages;

public class RegisterController implements Serializable{
	
	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	private Client client = new Client();
	private Administrator administrator = new Administrator();
	
	private String repitePassword;
	
	/*SERVICE A UTILIZAR*/
	@ManagedProperty("#{registerService}")  
	private RegisterService registerService;
	
	/*METODOS ASOCIADOS A ACCIONES DE FORMULARIO*/
	public String registerClient()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(registerService.existUser(client.getUsername()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,Messages.getString("username_error_sumary"),Messages.getString("username_error_detail"));
			facesContext.addMessage("registroUsuarioForm:username",message);
		}
		if(registerService.existEmail(client.getContactInfo().getEmail()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,Messages.getString("email_error_sumary"),Messages.getString("email_error_detail"));
			facesContext.addMessage("registroUsuarioForm:email",message);
		}
		if(repitePassword==null || !repitePassword.equals(client.getPassword()))
		{
			FacesUtil.addErrorMessageField("registroUsuarioForm:passwordRepite", Messages.getString("error.repitepassword"));
		}
		
		if(facesContext.getMessageList().size()==0)
		{
			if(registerService.register(client))
			{
				//Llamamos al service de registro para que guarde el cliente
				return "registro-ok";
			}
			else
			{
				FacesUtil.addErrorMessage(Messages.getString("error.sistema"));
			}
		}
		return null;
				
	}
	
	public String registerAdministrator()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(registerService.existUser(administrator.getUsername()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,Messages.getString("username_error_sumary"),Messages.getString("username_error_detail"));
			facesContext.addMessage("registroAdminForm:username",message);
		}
		if(registerService.existEmail(administrator.getContactInfo().getEmail()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,Messages.getString("email_error_sumary"),Messages.getString("email_error_detail"));
			facesContext.addMessage("registroAdminForm:email",message);
		}
		
		if(repitePassword==null || !repitePassword.equals(administrator.getPassword()))
		{
			FacesUtil.addErrorMessageField("registroAdminForm:passwordRepite", Messages.getString("error.repitepassword"));
		}
		
		if(facesContext.getMessageList().size()==0)
		{
			if(registerService.register(administrator))
			{
				//Llamamos al service de registro para que guarde el cliente
				return "registro-ok";
			}
			else
			{
				FacesUtil.addErrorMessage(Messages.getString("error.sistema"));
			}
		}
		
		return null;
				
	}

	/*GETTERS AND SETTERS*/
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public String getRepitePassword() {
		return repitePassword;
	}

	public void setRepitePassword(String repitePassword) {
		this.repitePassword = repitePassword;
	}
	
	
}
