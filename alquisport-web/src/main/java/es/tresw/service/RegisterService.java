package es.tresw.service;

<<<<<<< Updated upstream
import es.tresw.db.beans.Client;
=======
import org.springframework.stereotype.Service;

import es.tresw.db.entities.Client;
>>>>>>> Stashed changes

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
