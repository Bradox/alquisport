package es.tresw.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {
	
	public static void addErrorMessage(String resumen, String detalle)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,resumen,detalle);
		facesContext.addMessage(null,message);
	}
	
	public static void addErrorMessage(String mensaje)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje,mensaje);
		facesContext.addMessage(null,message);
	}
	
	public static void addErrorMessageField(String field, String mensaje)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,mensaje,mensaje);
		facesContext.addMessage(field,message);
	}
	
	public static void addErrorMessageField(String field, String resumen, String detalle)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,resumen,detalle);
		facesContext.addMessage(field,message);
	}
	
	
	public static void addInfoMessage(String resumen, String detalle)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,resumen,detalle);
		facesContext.addMessage(null,message);
	}
	
	public static void addInfoMessage(String mensaje)
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,mensaje,mensaje);
		facesContext.addMessage(null,message);
	}

}
