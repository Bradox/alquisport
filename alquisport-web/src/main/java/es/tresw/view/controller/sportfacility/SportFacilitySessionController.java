package es.tresw.view.controller.sportfacility;

import java.io.Serializable;

import es.tresw.db.entities.Court;
import es.tresw.db.entities.SportFacility;

public class SportFacilitySessionController implements Serializable{
	
	private SportFacility sportFacility;
	private Court court;
	
	/*ACTIONS*/
	/**
	 * Carga en la sesion la instalacion deportiva y se va a la pagina de detalle
	 * @param sf
	 */
	public String sportFacilityDetail(SportFacility sf)
	{
		//Cargamos en sesion el objeto
		this.sportFacility = sf;
		//Nos vamos a la vista de detalle
		return "/insdeportiva/id-nueva.xhtml";
	}
	
	public void courtDetail(Court sf)
	{
		
	}
	
	
	/*GETTERS AND SETTERS*/
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
