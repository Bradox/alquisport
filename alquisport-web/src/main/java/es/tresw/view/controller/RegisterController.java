package es.tresw.view.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Client;
import es.tresw.service.RegisterService;

public class RegisterController implements Serializable{
	
	private static final Logger logger = Logger.getLogger(RegisterController.class);
	
	private Client client = new Client();
	private Administrator administrator = new Administrator();
	
	/*SERVICE A UTILIZAR*/
	@ManagedProperty("#{registerService}")  
	private RegisterService registerService;
	
	
	/*METODOS ASOCIADOS A ACCIONES DE FORMULARIO*/
	public String registerClient()
	{
		logger.debug("Esto es un mensaje de debug");
		logger.info("Esto es un mensaje de info");
		logger.error("Esto es un mensaje de error");
		logger.warn("Esto es un mensaje de warn");
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle rb =  ResourceBundle.getBundle("ValidationMessages");
		if(registerService.existUser(client.getLogin()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,rb.getString("username_error_sumary"),rb.getString("username_error_detail"));
			facesContext.addMessage("register:username",message);
		}
		if(registerService.existEmail(client.getContactInfo().getEmail()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,rb.getString("email_error_sumary"),rb.getString("email_error_detail"));
			facesContext.addMessage("register:email",message);
		}
		
		if(facesContext.getMessageList().size()==0)
		{
			if(registerService.register(client))
			{
				//Llamamos al service de registro para que guarde el cliente
				System.out.println("Se ha registrado el siguiente cliente: "+client);
				return "registro-ok";
			}
			else
			{
				System.out.println("el service no llega!");
				return "registro-error";
			}
		}
		else
			return null;
				
	}
	
	public String registerAdministrator()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ResourceBundle rb =  ResourceBundle.getBundle("ValidationMessages");
		if(registerService.existUser(administrator.getLogin()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,rb.getString("username_error_sumary"),rb.getString("username_error_detail"));
			facesContext.addMessage("register:username",message);
		}
		if(registerService.existEmail(administrator.getContactInfo().getEmail()))
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,rb.getString("email_error_sumary"),rb.getString("email_error_detail"));
			facesContext.addMessage("register:email",message);
		}
		
		if(facesContext.getMessageList().size()==0)
		{
			if(registerService.register(administrator))
			{
				//Llamamos al service de registro para que guarde el cliente
				System.out.println("Se ha registrado el siguiente administrador: "+administrator);
				return "registro-ok";
			}
			else
			{
				System.out.println("el service no llega!");
				return "registro-error";
			}
		}
		else
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
}
