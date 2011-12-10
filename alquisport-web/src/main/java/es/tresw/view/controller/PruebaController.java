package es.tresw.view.controller;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
@RequestScoped
public class PruebaController implements Serializable{
	
	@NotNull
	@Size(min=1,message="{campo_obligatorio}")
	private String campo1;
	@NotNull
	@Size(min=1,message="{campo_obligatorio}")
	private String campo2;
	
	public String action()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		if (campo1==null || campo1.length()<5) {
			context.addMessage(null,
			new FacesMessage("UserID required"));
		}
		System.out.println("campo1 = "+campo1);
		System.out.println("campo2 = "+campo2);
		if (context.getMessageList().size() > 0) {
			return(null);
		} else {
			return("registro/registro-ok");
		}
	}
	
	
	public String getCampo1() {
		return campo1;
	}
	
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}
	public String getCampo2() {
		return campo2;
	}
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}
	
	

}
