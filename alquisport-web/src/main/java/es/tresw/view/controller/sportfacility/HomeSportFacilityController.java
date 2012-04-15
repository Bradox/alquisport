package es.tresw.view.controller.sportfacility;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.SportFacility;
import es.tresw.service.PruebaService;
import es.tresw.service.SportFacilityService;
import es.tresw.view.controller.user.UserSessionController;

public class HomeSportFacilityController implements Serializable{
	
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	@ManagedProperty("#{userSessionController}")
	private UserSessionController userSessionController;
	
	private List<SportFacility> instalaciones;
	
	public HomeSportFacilityController()
	{
		
	}
	
	/*METODOS PUBLICOS*/
	@PostConstruct
	public void init()
	{
		//Cogemos el usuario de la sesion
		Administrator a = userSessionController.getAdministrator();
		if(a!=null)
		{
			instalaciones = sportFacilityService.getSportFacilityByAdministrator(a);
		}
	}
	
	
	public String getPrueba()
	{
		return sportFacilityService.prueba();
	}

	public UserSessionController getUserSessionController() {
		return userSessionController;
	}


	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}


	public List<SportFacility> getInstalaciones() {
		return instalaciones;
	}


	public void setInstalaciones(List<SportFacility> instalaciones) {
		this.instalaciones = instalaciones;
	}

	public SportFacilityService getSportFacilityService() {
		return sportFacilityService;
	}

	public void setSportFacilityService(SportFacilityService sportFacilityService) {
		this.sportFacilityService = sportFacilityService;
	}
}
