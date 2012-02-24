package es.tresw.view.controller.sportfacility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;
import es.tresw.service.SportFacilityService;
import es.tresw.util.Messages;

public class SportFacilityController implements Serializable{
	
	private SportFacility sportFacility;
	
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	private Long province;
	private Long municipality;
	
	private List<SelectItem> municipios;
	private List<SelectItem> provincias;
	
	public SportFacilityController()
	{
		sportFacility = new SportFacility();
		sportFacility.setAddress(new Address());
		sportFacility.setContactInfo(new ContactInfo());
		provincias = new ArrayList<SelectItem>();
		municipios = new ArrayList<SelectItem>();
	}
	
	public String createSportFacility()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		//leemos la provincia y el municipio y actualizamos
		if(province!=null && municipality!=null)
		{
			Province p = sportFacilityService.getProvince(province);
			sportFacility.getAddress().setProvince(p);
			
			Municipality m = sportFacilityService.getMunicipality(municipality);
			sportFacility.getAddress().setMunicipality(m);
			
			//Guardamos
			sportFacilityService.createSportFacility(sportFacility);
		}
		else
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"),
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"));
			facesContext.addMessage("register:username",message);
			return null;
		}
		
		
		
		return "resumen-instalacion-deportiva";
	}

	
	public void handleProvinceChange() {  
        if(province !=null)
        {
        	List<Municipality> municipiosEntity = sportFacilityService.getMunicipalityByProvince(province);
        	municipios = new ArrayList<SelectItem>();
        	for(Municipality m:municipiosEntity)
        	{
        		SelectItem s = new SelectItem();
    			s.setLabel(m.getName());
    			s.setValue(m.getId());
    			municipios.add(s);
        	}
        }
        else  
        	municipios = new ArrayList<SelectItem>();
    }  
	
	
	/*GETTERS AND SETTERS*/

	public SportFacility getSportFacility() {
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) {
		this.sportFacility = sportFacility;
	}

	public SportFacilityService getSportFacilityService() {
		return sportFacilityService;
	}

	public void setSportFacilityService(SportFacilityService sportFacilityService) {
		this.sportFacilityService = sportFacilityService;
	}

	public Long getProvince() {
		return province;
	}

	public void setProvince(Long province) {
		this.province = province;
	}

	public Long getMunicipality() {
		return municipality;
	}

	public void setMunicipality(Long municipality) {
		this.municipality = municipality;
	}

	public List<SelectItem> getMunicipios() {
		handleProvinceChange();
		return municipios;
	}
	public void setMunicipios(List<SelectItem> municipios) {
		this.municipios = municipios;
	}
	public List<SelectItem> getProvincias() {
		provincias = new ArrayList<SelectItem>();
		List<Province> provinciasEntity = sportFacilityService.getProvinces();
		for(Province p:provinciasEntity)
		{
			SelectItem s = new SelectItem();
			s.setLabel(p.getName());
			s.setValue(p.getId());
			provincias.add(s);
		}
		return provincias;
	}
	public void setProvincias(List<SelectItem> provincias) {
		this.provincias = provincias;
	}

	
}
