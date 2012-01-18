package es.tresw.view.controller.sportfacility;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import es.tresw.db.entities.SportFacility;

@ManagedBean
@SessionScoped
public class SportFacilityController {
	
	private SportFacility sportFacility;
	
	
	

	public SportFacility getSportFacility() {
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}
	
	

}
