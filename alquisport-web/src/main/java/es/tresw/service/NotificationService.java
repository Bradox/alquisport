package es.tresw.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;

import es.tresw.util.Messages;

/**
 * Clase que va a tener el envío de todas las notificaciones que se harán desde la aplicación
 * @author Sergio
 *
 */
public class NotificationService {
	
	@Autowired
	MailService mailService = new MailService();
	
	private String header;
	private String foot;
	private String mailFrom;
	
	public NotificationService()
	{
		header = Messages.getStringEmail("contenido.cabecera");
		foot = Messages.getStringEmail("contenido.pie");
		mailFrom = Messages.getStringEmail("mailfrom");
		
	}

	public void recoverPassword(String mailTo, String newPassword)
	{
		//Genero el contenido del mensaje
		String contenido = MessageFormat.format(Messages.getStringEmail("recoverpassword.contenido"), newPassword);
		String text = header + contenido + foot;
		String subject = Messages.getStringEmail("recoverpassword.asunto");
		mailService.sendMail(mailFrom, mailTo, subject, text);
	}
	
	
}
