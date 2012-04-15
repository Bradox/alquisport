package es.tresw.view.controller.sportfacility;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
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

public class GeneralInfoController {
	
	/*Bean en session*/
	@ManagedProperty("#{sportFacilitySessionController}")
	private SportFacilitySessionController sportFacilitySessionController;
	
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
	
	private Boolean mostrarFormulario;
	
	/*Lista de los select*/
	private List<SelectItem> municipios;
	private List<SelectItem> provincias;
	
	
	/*Constructor*/
	public GeneralInfoController()
	{
		loadData();
		
	}
	
	@PostConstruct
	public void init(){  
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"error validate2", null);  
		facesContext.addMessage("myform:input2",msg);  
		System.out.println("add message test");                 
	}  
	
	/*Acciones*/
	public String saveSportFacility()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if(validate(facesContext))
		{
			Province p = sportFacilityService.getProvince(province);
			sportFacilitySessionController.getSportFacility().getAddress().setProvince(p);
			
			Municipality m = sportFacilityService.getMunicipality(municipality);
			sportFacilitySessionController.getSportFacility().getAddress().setMunicipality(m);
			
			//Actualizamos el objeto en sesion
			writeSession();
			
			//Guardamos
			sportFacilityService.createSportFacility(sportFacilitySessionController.getSportFacility());
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
		if(sportFacilitySessionController ==null || sportFacilitySessionController.getSportFacility()==null)
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
		if(sportFacilitySessionController!=null && sportFacilitySessionController.getSportFacility()!=null)
		{
			name = sportFacilitySessionController.getSportFacility().getName();
			urlName = sportFacilitySessionController.getSportFacility().getUrlName();
			if(sportFacilitySessionController.getSportFacility().getContactInfo()!=null)
			{
				phone1 = sportFacilitySessionController.getSportFacility().getContactInfo().getTelephone1();
				phone2 = sportFacilitySessionController.getSportFacility().getContactInfo().getTelephone2();
				email = sportFacilitySessionController.getSportFacility().getContactInfo().getEmail();
			}
			if(sportFacilitySessionController.getSportFacility().getAddress()!=null)
			{
				province = sportFacilitySessionController.getSportFacility().getAddress().getProvince().getId();
				municipality = sportFacilitySessionController.getSportFacility().getAddress().getMunicipality().getId();
				address = sportFacilitySessionController.getSportFacility().getAddress().getAddress();
				zipCode = sportFacilitySessionController.getSportFacility().getAddress().getZipCode();
				zone = sportFacilitySessionController.getSportFacility().getAddress().getZone().getName();
			}
			getHere = sportFacilitySessionController.getSportFacility().getGetHere();
			mostrarFormulario = true;
		}
		else{
			//Tenemos que mostrar el error y que no aparezca el formulario
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("sportfacility.no_session"),
					Messages.getString("sportfacility.no_session"));
			facesContext.addMessage(null,message);
			
			mostrarFormulario = false;
		}
	}
	
	public String postLoad()
	{
		System.out.println("entra en postload");
		if(sportFacilitySessionController!=null && sportFacilitySessionController.getSportFacility()!=null)
		{
			System.out.println("hay sesion");
			return null;
		}else
		{
			System.out.println("no hay sesion");
			return "sinpermisos.xhtml";
		}
	}
	
	private void writeSession()
	{
		if(sportFacilitySessionController!=null && sportFacilitySessionController.getSportFacility()!=null)
		{
			sportFacilitySessionController.getSportFacility().setName(name);
			sportFacilitySessionController.getSportFacility().setUrlName(urlName);
			if(sportFacilitySessionController.getSportFacility().getContactInfo()!=null)
			{
				sportFacilitySessionController.getSportFacility().getContactInfo().setTelephone1(phone1);
				sportFacilitySessionController.getSportFacility().getContactInfo().setTelephone2(phone2);
				sportFacilitySessionController.getSportFacility().getContactInfo().setEmail(email);
			}
			if(sportFacilitySessionController.getSportFacility().getAddress()!=null)
			{
				sportFacilitySessionController.getSportFacility().getAddress().getProvince().setId(province);
				sportFacilitySessionController.getSportFacility().getAddress().getMunicipality().setId(municipality);
				sportFacilitySessionController.getSportFacility().getAddress().setAddress(address);
				sportFacilitySessionController.getSportFacility().getAddress().setZipCode(zipCode);
				sportFacilitySessionController.getSportFacility().getAddress().getZone().setName(zone);
			}
			sportFacilitySessionController.getSportFacility().setGetHere(getHere);
		}
	}
	
	
	/*GETTERS y SETTERS*/
	public SportFacilitySessionController getSportFacilitySessionController() {
		return sportFacilitySessionController;
	}

	public void setSportFacilitySessionController(
			SportFacilitySessionController sportFacilitySessionController) {
		this.sportFacilitySessionController = sportFacilitySessionController;
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
	public Boolean getMostrarFormulario() {
		return mostrarFormulario;
	}
	public void setMostrarFormulario(Boolean mostrarFormulario) {
		this.mostrarFormulario = mostrarFormulario;
	}

}
