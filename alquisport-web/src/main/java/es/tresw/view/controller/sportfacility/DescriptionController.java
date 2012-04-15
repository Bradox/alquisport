package es.tresw.view.controller.sportfacility;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import es.tresw.service.SportFacilityService;

public class DescriptionController implements Serializable{
	
	/*Bean en session*/
	@ManagedProperty("#{sportFacilitySessionController}")
	private SportFacilitySessionController sportFacilitySessionController;
	
	/*Services*/
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	/*campos del formulario*/
	private String description;

	
	/*CONSTRUCTORES e INIT*/
	public DescriptionController()
	{}
	
	@PostConstruct
	public void init()
	{
		//inicializamos la variable description con el valor actual
		description = sportFacilitySessionController.getSportFacility().getDescription();
	}
	
	/*ACCIONES*/
	public String guardar()
	{
		sportFacilitySessionController.getSportFacility().setDescription(description);
		sportFacilityService.updateSportFacility(sportFacilitySessionController.getSportFacility());
		return "resumen-instalacion-deportiva";
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
