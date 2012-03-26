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
import es.tresw.view.controller.AdminSessionController;

public class GeneralInfoController {
	
	/*Bean en session*/
	@ManagedProperty("#{adminSessionController}")
	private AdminSessionController adminSessionController;
	
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
			adminSessionController.getSportFacility().getAddress().setProvince(p);
			
			Municipality m = sportFacilityService.getMunicipality(municipality);
			adminSessionController.getSportFacility().getAddress().setMunicipality(m);
			
			//Actualizamos el objeto en sesion
			writeSession();
			
			//Guardamos
			sportFacilityService.createSportFacility(adminSessionController.getSportFacility());
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
		if(adminSessionController ==null || adminSessionController.getSportFacility()==null)
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
		if(adminSessionController!=null && adminSessionController.getSportFacility()!=null)
		{
			name = adminSessionController.getSportFacility().getName();
			urlName = adminSessionController.getSportFacility().getUrlName();
			if(adminSessionController.getSportFacility().getContactInfo()!=null)
			{
				phone1 = adminSessionController.getSportFacility().getContactInfo().getTelephone1();
				phone2 = adminSessionController.getSportFacility().getContactInfo().getTelephone2();
				email = adminSessionController.getSportFacility().getContactInfo().getEmail();
			}
			if(adminSessionController.getSportFacility().getAddress()!=null)
			{
				province = adminSessionController.getSportFacility().getAddress().getProvince().getId();
				municipality = adminSessionController.getSportFacility().getAddress().getMunicipality().getId();
				address = adminSessionController.getSportFacility().getAddress().getAddress();
				zipCode = adminSessionController.getSportFacility().getAddress().getZipCode();
				zone = adminSessionController.getSportFacility().getAddress().getZone().getName();
			}
			getHere = adminSessionController.getSportFacility().getGetHere();
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
		if(adminSessionController!=null && adminSessionController.getSportFacility()!=null)
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
		if(adminSessionController!=null && adminSessionController.getSportFacility()!=null)
		{
			adminSessionController.getSportFacility().setName(name);
			adminSessionController.getSportFacility().setUrlName(urlName);
			if(adminSessionController.getSportFacility().getContactInfo()!=null)
			{
				adminSessionController.getSportFacility().getContactInfo().setTelephone1(phone1);
				adminSessionController.getSportFacility().getContactInfo().setTelephone2(phone2);
				adminSessionController.getSportFacility().getContactInfo().setEmail(email);
			}
			if(adminSessionController.getSportFacility().getAddress()!=null)
			{
				adminSessionController.getSportFacility().getAddress().getProvince().setId(province);
				adminSessionController.getSportFacility().getAddress().getMunicipality().setId(municipality);
				adminSessionController.getSportFacility().getAddress().setAddress(address);
				adminSessionController.getSportFacility().getAddress().setZipCode(zipCode);
				adminSessionController.getSportFacility().getAddress().getZone().setName(zone);
			}
			adminSessionController.getSportFacility().setGetHere(getHere);
		}
	}
	
	
	/*GETTERS y SETTERS*/
	public AdminSessionController getAdminSessionController() {
		return adminSessionController;
	}
	public void setAdminSessionController(AdminSessionController sessionController) {
		this.adminSessionController = sessionController;
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
