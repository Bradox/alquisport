package es.tresw.db.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.Appearance;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@Table(name="SPORT_FACILITY",catalog="Alquisport")
public class SportFacility 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Pattern(regexp="[a-z]*",message="{identificador_incorrecto}")
	@Column(name="URL_NAME", nullable=false, length=255)
	private String urlName;
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Column(name="GET_HERE", columnDefinition="TEXT")
	private String getHere;
	@Column(name="DESCRIPTION", columnDefinition="TEXT")
	private String description;
	@Column(name="STATE", nullable=false, length=2)
	private Integer state;
	@Embedded
	private Address address;
	@Embedded
	private ContactInfo contactInfo;
	@Embedded
	private Appearance appearance;
	@OneToMany
	@JoinTable(name = "SPORT_FACILITY_FEATURES", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID") })
	private List<Feature> features;
	@OneToMany	
	@JoinTable(name = "SPORT_FACIlITY_IMAGE", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "IMAGE_ID") })
	private List<Image> images;
	@OneToMany (mappedBy="sportFacility")
	private List<Administrator> administrators;
	@OneToMany(mappedBy="sportFacility")	
	private List<DayClosed> daysClosed;
	@OneToMany(mappedBy="sportFacility")	
	private List<SportFacilityMember> members;
	
	public SportFacility()
	{
		
	}

	
	public SportFacility(Long id,String name, String getHere, String description, Integer state, List<Feature> features, List<Image> images, List<Administrator> administrators, Address address, ContactInfo contactInfo, Appearance appearance, List<DayClosed> daysClosed, List<SportFacilityMember> members) 
	{
		this.id = id;
		this.name = name;
		this.getHere = getHere;
		this.description = description;
		this.state = state;
		this.features = features;
		this.images = images;
		this.administrators = administrators;
		this.address = address;
		this.contactInfo = contactInfo;
		this.appearance = appearance;
		this.daysClosed=daysClosed;
		this.members=members;
	}


	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getGetHere()
	{
		return getHere;
	}

	public void setGetHere(String getHere)
	{
		this.getHere = getHere;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public Integer getState() 
	{
		return state;
	}

	public void setState(Integer state) 
	{
		this.state = state;
	}

	public List<Feature> getFeatures() 
	{
		return features;
	}

	public void setFeatures(List<Feature> features) 
	{
		this.features = features;
	}

	public List<Image> getImagenes() 
	{
		return images;
	}

	public void setImages(List<Image> images) 
	{
		this.images = images;
	}

	public List<Administrator> getAdministrators() 
	{
		return administrators;
	}

	public void setAdministrators(List<Administrator> administrators) 
	{
		this.administrators = administrators;
	}

	public Address getAddress() 
	{
		return address;
	}

	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public ContactInfo getContactInfo() 
	{
		return contactInfo;
	}

	public void setContactInfo(ContactInfo contactInfo) 
	{
		this.contactInfo = contactInfo;
	}

	public Appearance getAppearance() 
	{
		return appearance;
	}

	public void setAppearance(Appearance appearance) 
	{
		this.appearance = appearance;
	}


	public List<DayClosed> getDaysClosed() 
	{
		return daysClosed;
	}


	public void setDaysClosed(List<DayClosed> daysClosed)
	{
		this.daysClosed = daysClosed;
	}


	public List<SportFacilityMember> getMembers() {
		return members;
	}


	public void setMembers(List<SportFacilityMember> members) {
		this.members = members;
	}


	public String getUrlName() {
		return urlName;
	}


	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}


	public List<Image> getImages() {
		return images;
	}
	
	

}
