package es.tresw.view.controller;

import javax.inject.Named;

import es.tresw.db.beans.Client;

@Named("register")
public class RegisterController {
	
	private Client client = new Client();
	
	/*SERVICE A UTILIZAR*/
	
	public void registerFinalUser()
	{
		System.out.println("HA ENTRADO Y REGISTRADO AL USUARIO");
	}

}
