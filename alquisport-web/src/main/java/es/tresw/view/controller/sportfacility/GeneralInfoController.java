package es.tresw.view.controller.sportfacility;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.service.SportFacilityService;
import es.tresw.util.Messages;
import es.tresw.view.controller.AdminSessionController;

public class GeneralInfoController {
	
	/*Bean en session*/
	@ManagedProperty("#{adminSessionController}")
	private AdminSessionController sessionController;
	
	/*Services*/
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	/*campos del formulario*/
	@Size(min=1,max=255,message="{campo_obligatorio}")
	private String name;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Pattern(regexp="[a-z]*",message="{identificador_incorrecto}")
	private String urlName;
	
	@Size(min=9,max=15,message="{telefono_incorrecto}")
	private String phone1;
	
	@Size(min=0,max=15,message="{telefono_incorrecto}")
	private String phone2;
	
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Email(message="{email_incorrecto}")
	private String email;
	
	@NotNull
	private Long province;
	
	@NotNull
	private Long municipality;
	
	@Size(min=1,max=255,message="{campo_obligatorio}")
	private String address;
	
	@Size(min=5,max=5,message="{zipcode_incorrecto}")
	private String zipCode;
	private String zone;
	private String getHere;
	
	/*Lista de los select*/
	private List<SelectItem> municipios;
	private List<SelectItem> provincias;
	
	
	/*Constructor*/
	public GeneralInfoController()
	{
		loadData();
	}
	
	/*Acciones*/
	public String createSportFacility()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if(validate(facesContext))
		{
			Province p = sportFacilityService.getProvince(province);
			sessionController.getSportFacility().getAddress().setProvince(p);
			
			Municipality m = sportFacilityService.getMunicipality(municipality);
			sessionController.getSportFacility().getAddress().setMunicipality(m);
			
			//Actualizamos el objeto en sesion
			writeSession();
			
			//Guardamos
			sportFacilityService.createSportFacility(sessionController.getSportFacility());
		}else
		{
			return null;
		}
		return "resumen-instalacion-deportiva";
	}
	
	/*Manejadores de eventos*/
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
	
	
	/*Metodos privados*/
	private boolean validate(FacesContext facesContext)
	{
		boolean r = true;
		
		//Primero tenemos que revisar que existe en sesion una instalacion
		if(sessionController.getSportFacility()==null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("sportfacility.no_session"),
					Messages.getString("sportfacility.no_session"));
			facesContext.addMessage("generalinfo:message",message);
			r = false;
		}else if(province==null || municipality==null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"),
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"));
			facesContext.addMessage("register:username",message);
			r = false;
		}
		
		return r;
	}
	
	private void loadData()
	{
		if(sessionController.getSportFacility()!=null)
		{
			name = sessionController.getSportFacility().getName();
			urlName = sessionController.getSportFacility().getUrlName();
			if(sessionController.getSportFacility().getContactInfo()!=null)
			{
				phone1 = sessionController.getSportFacility().getContactInfo().getTelephone1();
				phone2 = sessionController.getSportFacility().getContactInfo().getTelephone2();
				email = sessionController.getSportFacility().getContactInfo().getEmail();
			}
			if(sessionController.getSportFacility().getAddress()!=null)
			{
				province = sessionController.getSportFacility().getAddress().getProvince().getId();
				municipality = sessionController.getSportFacility().getAddress().getMunicipality().getId();
				address = sessionController.getSportFacility().getAddress().getAddress();
				zipCode = sessionController.getSportFacility().getAddress().getZipCode();
				zone = sessionController.getSportFacility().getAddress().getZone().getName();
			}
			getHere = sessionController.getSportFacility().getGetHere();
			
		}
	}
	
	private void writeSession()
	{
		if(sessionController.getSportFacility()!=null)
		{
			sessionController.getSportFacility().setName(name);
			sessionController.getSportFacility().setUrlName(urlName);
			if(sessionController.getSportFacility().getContactInfo()!=null)
			{
				sessionController.getSportFacility().getContactInfo().setTelephone1(phone1);
				sessionController.getSportFacility().getContactInfo().setTelephone2(phone2);
				sessionController.getSportFacility().getContactInfo().setEmail(email);
			}
			if(sessionController.getSportFacility().getAddress()!=null)
			{
				sessionController.getSportFacility().getAddress().getProvince().setId(province);
				sessionController.getSportFacility().getAddress().getMunicipality().setId(municipality);
				sessionController.getSportFacility().getAddress().setAddress(address);
				sessionController.getSportFacility().getAddress().setZipCode(zipCode);
				sessionController.getSportFacility().getAddress().getZone().setName(zone);
			}
			sessionController.getSportFacility().setGetHere(getHere);
		}
	}
	
	
	/*GETTERS y SETTERS*/
	public AdminSessionController getSessionController() {
		return sessionController;
	}
	public void setSessionController(AdminSessionController sessionController) {
		this.sessionController = sessionController;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getGetHere() {
		return getHere;
	}
	public void setGetHere(String getHere) {
		this.getHere = getHere;
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
	public SportFacilityService getSportFacilityService() {
		return sportFacilityService;
	}
	public void setSportFacilityService(SportFacilityService sportFacilityService) {
		this.sportFacilityService = sportFacilityService;
	}

}
