package es.tresw.view.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

import es.tresw.db.beans.Client;
import es.tresw.service.RegisterService;

@ManagedBean
@RequestScoped
public class RegisterController {
	
	private Client client = new Client();
	
	/*SERVICE A UTILIZAR*/
	@Autowired
	private RegisterService registerService;
	
	
	/*METODOS ASOCIADOS A ACCIONES DE FORMULARIO*/
	public void registerClient()
	{
		if(registerService!=null)
		{
			//Llamamos al service de registro para que guarde el cliente
			registerService.register(client);
			System.out.println("HA ENTRADO Y REGISTRADO AL USUARIO");
		}
		else
			System.out.println("el service no llega!");
	}

	/*GETTERS AND SETTERS*/
	public Client getClient() {
		System.out.println("en el get");
		client.setLogin("ESTE SOY YO");
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

}
