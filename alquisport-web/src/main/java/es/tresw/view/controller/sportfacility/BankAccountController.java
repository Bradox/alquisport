package es.tresw.view.controller.sportfacility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;
import javax.validation.constraints.Size;

import es.tresw.db.entities.Province;
import es.tresw.service.SportFacilityService;

public class BankAccountController implements Serializable{
	
	/*Bean en session*/
	@ManagedProperty("#{sportFacilitySessionController}")
	private SportFacilitySessionController sportFacilitySessionController;
	
	/*Services*/
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	/*campos del formulario*/
	@Size(min=4, max=4, message="{bankaccount.error.ccc1}")
	private String ccc1;
	@Size(min=4, max=4, message="{bankaccount.error.ccc2}")
	private String ccc2;
	@Size(min=2, max=2, message="{bankaccount.error.ccc3}")
	private String ccc3;
	@Size(min=10, max=10, message="{bankaccount.error.ccc4}")
	private String ccc4;
	private String entidadBancaria;
	private Integer tipoInscripcion;
	private String direccionFactura;
	private String cifFactura;
	private String nombreFactura;
	

	
	/*CONSTRUCTORES e INIT*/
	public BankAccountController()
	{}
	
	@PostConstruct
	public void init()
	{
		//inicializamos los valores del controller
		//TODO cargar los valores de la sesion en los de la vista
	}
	
	/*ACCIONES*/
	public String guardar()
	{
		//TODO tenemos que actualizar los valores del formulario
		sportFacilityService.updateSportFacility(sportFacilitySessionController.getSportFacility());
		return "resumen-instalacion-deportiva";
	}
	
	public List<SelectItem> getTiposDePago()
	{
		List<SelectItem> tipos = new ArrayList<SelectItem>();
		SelectItem s1 = new SelectItem();
		s1.setLabel("Semanal");
		s1.setValue(new Integer(1));
		tipos.add(s1);
		
		SelectItem s2 = new SelectItem();
		s2.setLabel("Mensual");
		s2.setValue(new Integer(2));
		tipos.add(s2);
		
		return tipos;
	}
	
	/*GETTERS AND SETTERS*/
	public SportFacilitySessionController getSportFacilitySessionController() {
		return sportFacilitySessionController;
	}

	public void setSportFacilitySessionController(
			SportFacilitySessionController sportFacilitySessionController) {
		this.sportFacilitySessionController = sportFacilitySessionController;
	}

	public SportFacilityService getSportFacilityService() {
		return sportFacilityService;
	}

	public void setSportFacilityService(SportFacilityService sportFacilityService) {
		this.sportFacilityService = sportFacilityService;
	}

	public String getCcc1() {
		return ccc1;
	}

	public void setCcc1(String ccc1) {
		this.ccc1 = ccc1;
	}

	public String getCcc2() {
		return ccc2;
	}

	public void setCcc2(String ccc2) {
		this.ccc2 = ccc2;
	}

	public String getCcc3() {
		return ccc3;
	}

	public void setCcc3(String ccc3) {
		this.ccc3 = ccc3;
	}

	public String getCcc4() {
		return ccc4;
	}

	public void setCcc4(String ccc4) {
		this.ccc4 = ccc4;
	}

	public String getEntidadBancaria() {
		return entidadBancaria;
	}

	public void setEntidadBancaria(String entidadBancaria) {
		this.entidadBancaria = entidadBancaria;
	}

	public Integer getTipoInscripcion() {
		return tipoInscripcion;
	}

	public void setTipoInscripcion(Integer tipoInscripcion) {
		this.tipoInscripcion = tipoInscripcion;
	}

	public String getDireccionFactura() {
		return direccionFactura;
	}

	public void setDireccionFactura(String direccionFactura) {
		this.direccionFactura = direccionFactura;
	}

	public String getCifFactura() {
		return cifFactura;
	}

	public void setCifFactura(String cifFactura) {
		this.cifFactura = cifFactura;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

}
