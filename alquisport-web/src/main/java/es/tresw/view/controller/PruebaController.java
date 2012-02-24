package es.tresw.view.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedProperty;
import javax.faces.model.SelectItem;

import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.service.SportFacilityService;

public class PruebaController implements Serializable {

	private Long municipio;
	private Long provincia;
	private List<SelectItem> municipios;
	private List<SelectItem> provincias;
	
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	public PruebaController()
	{
		provincias = new ArrayList<SelectItem>();
		municipios = new ArrayList<SelectItem>();
	}
	
	public void handleProvinceChange() {  
		System.out.println("entra y la provincia es = "+provincia);
        if(provincia !=null && !provincia.equals(""))
        {
        	List<Municipality> municipiosEntity = sportFacilityService.getMunicipalityByProvince(provincia);
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
	
	
	public Long getMunicipio() {
		return municipio;
	}
	public void setMunicipio(Long municipio) {
		this.municipio = municipio;
	}
	public Long getProvincia() {
		return provincia;
	}
	public void setProvincia(Long provincia) {
		this.provincia = provincia;
	}
	public List<SelectItem> getMunicipios() {
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
	public SportFacilityService getSportFacilityService() {
		return sportFacilityService;
	}
	public void setSportFacilityService(SportFacilityService sportFacilityService) {
		this.sportFacilityService = sportFacilityService;
	}
	
	
	
	
}

					