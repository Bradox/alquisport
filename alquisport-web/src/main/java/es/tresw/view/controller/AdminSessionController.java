package es.tresw.view.controller;

import es.tresw.db.entities.Court;
import es.tresw.db.entities.SportFacility;

public class AdminSessionController {
	
	private SportFacility sportFacility;
	private Court court;
	
	
	public SportFacility getSportFacility() {
		return sportFacility;
	}
	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}
	public Court getCourt() {
		return court;
	}
	public void setCourt(Court court) {
		this.court = court;
	}
	
	
	

}
