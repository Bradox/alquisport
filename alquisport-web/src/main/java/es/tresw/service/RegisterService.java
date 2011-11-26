package es.tresw.service;

import es.tresw.db.beans.Client;

public class RegisterService {
	
	private String mensajeok;
	
	public RegisterService()
	{
		mensajeok = "ha entrado y todo es correcto";
	}
	
	 public boolean register(Client c)
	 {
		 //TODO registrar en BBDD el cliente
		 System.out.println(mensajeok);
		 
		 return true;
	 }

}
