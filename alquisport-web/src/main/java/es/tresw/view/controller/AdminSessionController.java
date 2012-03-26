package es.tresw.view.controller;

import java.io.Serializable;

import es.tresw.db.entities.Court;
import es.tresw.db.entities.SportFacility;

public class AdminSessionController implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SportFacility sportFacility;
	private Court court;
	
	public AdminSessionController()
	{
		System.out.println("Entra en el constructor AdminSessionController");
	}
	
	
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
