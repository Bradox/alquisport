package es.tresw.view.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import es.tresw.db.entities.Client;
import es.tresw.service.RegisterService;

@ManagedBean
@RequestScoped
public class RegisterController implements Serializable{
	
	private Client client = new Client();
	@NotNull(message="No puede ser null")
	@Size(min=5,max=10,message="{nombre}")
	private String prueba;
	
	/*SERVICE A UTILIZAR*/
	@ManagedProperty("#{registerService}")  
	private RegisterService registerService;
	
	
	/*METODOS ASOCIADOS A ACCIONES DE FORMULARIO*/
	public String registerClient()
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

	/*GETTERS AND SETTERS*/
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public RegisterService getRegisterService() {
		return registerService;
	}

	public void setRegisterService(RegisterService registerService) {
		this.registerService = registerService;
	}

	public String getPrueba() {
		return prueba;
	}

	public void setPrueba(String prueba) {
		this.prueba = prueba;
	}
	

}
