package es.tresw.view.controller.sportfacility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.AdministratorSportFacility;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;
import es.tresw.db.entities.Zone;
import es.tresw.service.SportFacilityService;
import es.tresw.util.Messages;
import es.tresw.view.controller.user.UserSessionController;

public class NewSportFacilityController implements Serializable{
	
	@ManagedProperty("#{sportFacilityService}")
	private SportFacilityService sportFacilityService;
	
	@ManagedProperty("#{userSessionController}")
	private UserSessionController userSessionController;
	
	@ManagedProperty("#{sportFacilitySessionController}")
	private SportFacilitySessionController sportFacilitySessionController;
	
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
	
	private Long province;
	
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
	
	public NewSportFacilityController()
	{
		
	}
	
	/*Acciones*/
	public String createSportFacility()
	{
		FacesContext facesContext = FacesContext.getCurrentInstance();
		
		if(validate(facesContext))
		{
			
			//Creamos el objeto SportFacility y lo metemos en BBDD
			SportFacility sf = new SportFacility();
			sf.setName(name);
			sf.setUrlName(urlName);
			
			ContactInfo ci = new ContactInfo();
			ci.setTelephone1(phone1);
			ci.setTelephone2(phone2);
			ci.setEmail(email);
			sf.setContactInfo(ci);
			
			Address a = new Address();
			a.setAddress(address);
			a.setZipCode(zipCode);
			
			//Guardamos la provincia
			Province p = sportFacilityService.getProvince(province);
			a.setProvince(p);
			Municipality m = sportFacilityService.getMunicipality(municipality);
			a.setMunicipality(m);
			
			//Creamos la zona y la guardamos
			Zone z = sportFacilityService.searchZone(zone, m);
			if(z==null)
			{
				z = new Zone();
				z.setName(zone);
				z.setMunicipality(m);
				sportFacilityService.createZone(z);
			}
			
			a.setZone(z);	
			sf.setAddress(a);
			
			sf.setGetHere(getHere);
			
			//Guardamos
			sportFacilityService.createSportFacility(sf);
			
			//Le decimos el administrador de la pista
			AdministratorSportFacility asf = new AdministratorSportFacility();
			asf.setSportFacility(sf);
			asf.setAdministrator(userSessionController.getAdministrator());
			userSessionController.createAdministrators(asf);
			
			Set<AdministratorSportFacility> adminsitradores = new HashSet<AdministratorSportFacility>();
			adminsitradores.add(asf);
			sf.setAdministratorSportFacilities(adminsitradores);
			
			//actualizo el objeto de la sesion metiendo el recien creado y nos vamos a la pagina resumen.
			//adminSessionController.setSportFacility(sf);
			
			return "resumen-instalacion-deportiva";
		}else
		{
			return null;
		}
		
	}
	
	public String getPrueba()
	{
		return "desde el controller";
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
	
	public List<SelectItem> getProvincias() {
		if(provincias==null || provincias.size()==0)
		{
			provincias = new ArrayList<SelectItem>();
			List<Province> provinciasEntity = sportFacilityService.getProvinces();
			for(Province p:provinciasEntity)
			{
				SelectItem s = new SelectItem();
				s.setLabel(p.getName());
				s.setValue(p.getId());
				provincias.add(s);
			}
		}
		return provincias;
	}
	
	/*public List<SelectItem> getMunicipios() {
		handleProvinceChange();
		return municipios;
	}*/
	
	public List<SelectItem> getMunicipios() {
		if(municipios==null || municipios.size()==0)
		{
			municipios = new ArrayList<SelectItem>();
			List<Municipality> listaMunicipios = sportFacilityService.getMunicipality();
			for(Municipality m:listaMunicipios)
			{
				SelectItem s = new SelectItem();
				s.setLabel(m.getName());
				s.setValue(m.getId());
				municipios.add(s);
			}
		}
		return municipios;
	}
	
	
	/*Metodos privados*/
	/**
	 * Validamos que el usuario haya elegido una provincia y un municipio.
	 * @param facesContext
	 * @return
	 */
	private boolean validate(FacesContext facesContext)
	{
		boolean r = true;
		System.out.println("provincia = "+province+"; municipio = "+municipality);
		
		if(province==null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"),
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"));
			facesContext.addMessage("nueva-instalacion:provincias",message);
			r = false;
		}
		
		if(municipality==null)
		{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"),
					Messages.getString("sportfacility.infogeneral.error.direccion_incorrecta"));
			facesContext.addMessage("nueva-instalacion:municipios",message);
			r = false;
		}
		
		return r;
	}
	
	/*GETTERS AND SETTERS*/
	
	public UserSessionController getUserSessionController() {
		return userSessionController;
	}


	public void setUserSessionController(UserSessionController userSessionController) {
		this.userSessionController = userSessionController;
	}

	public SportFacilityService getSportFacilityService() {
		return sportFacilityService;
	}

	public void setSportFacilityService(SportFacilityService sportFacilityService) {
		this.sportFacilityService = sportFacilityService;
	}

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

	public void setMunicipios(List<SelectItem> municipios) {
		this.municipios = municipios;
	}

	public void setProvincias(List<SelectItem> provincias) {
		this.provincias = provincias;
	}
	
	
	
}
