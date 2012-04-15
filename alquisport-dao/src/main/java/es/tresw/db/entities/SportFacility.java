package es.tresw.db.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.Appearance;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@Table(name="SPORT_FACILITY")
public class SportFacility 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Pattern(regexp="[a-z]*",message="{identificador_incorrecto}")
	@Column(name="URL_NAME", length=255)
	private String urlName;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Column(name="NAME", length=255)
	private String name;
	
	@Column(name="GET_HERE", columnDefinition="TEXT")
	private String getHere;
	
	@Column(name="DESCRIPTION", columnDefinition="TEXT")
	private String description;
	
	@NotNull
	@Column(name="STATE", length=2)
	private Integer state;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE", length = 19)
	private Date createDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LAST_MODIFIED_DATE", length = 19)
	private Date lastModifiedDate;
	
	@Valid
	@NotNull
	@Embedded
	private Address address;
	
	@Valid
	@Embedded
	private ContactInfo contactInfo;
	
	@Embedded
	private Appearance appearance;
	
	@OneToMany
	@JoinTable(name = "SPORT_FACILITY_FEATURES", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID") })
	private Set<Feature> features=new HashSet<Feature>();
	
	@OneToMany	
	@JoinTable(name = "SPORT_FACIlITY_IMAGE", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "IMAGE_ID") })
	private Set<Image> images=new HashSet<Image>();
	
	@OneToMany (mappedBy="sportFacility")
	private Set<AdministratorSportFacility> administratorSportFacilities=new HashSet<AdministratorSportFacility>();
	
	@OneToMany(mappedBy="sportFacility")	
	private Set<SportFacilityMember> members=new HashSet<SportFacilityMember>();
	
	@OneToMany(mappedBy="sportFacility")	
	private Set<Court> courts = new HashSet<Court>();

	@OneToMany
	@JoinTable(name = "SPORT_FACILITY_DAY", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "DAY_ID") })
	private Set<Day> days = new HashSet<Day>();

	@OneToMany
	@JoinTable(name = "SPORT_FACILITY_SPECIAL_DAY", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "DAY_ID") })
	private Set<SpecialDay> specialDays = new HashSet<SpecialDay>();
	
	public SportFacility()
	{
		
	}

	
	public SportFacility(String name, String getHere, String description, Integer state, Set<Feature> features, Set<Image> images, Set<AdministratorSportFacility> administratorSportFacilities, Address address, ContactInfo contactInfo, Appearance appearance, Set<SportFacilityMember> members, Set<Court> courts, Set<Day> days, Set<SpecialDay> specialDays) 
	{
		this.name = name;
		this.getHere = getHere;
		this.description = description;
		this.state = state;
		this.features = features;
		this.images = images;
		this.administratorSportFacilities = administratorSportFacilities;
		this.address = address;
		this.contactInfo = contactInfo;
		this.appearance = appearance;
		this.members=members;
		this.courts=courts;
		this.days=days;
		this.specialDays=specialDays;
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

	public Set<Feature> getFeatures() 
	{
		return features;
	}

	public void setFeatures(Set<Feature> features) 
	{
		this.features = features;
	}

	public Set<Image> getImagenes() 
	{
		return images;
	}

	public void setImages(Set<Image> images) 
	{
		this.images = images;
	}

	public Set<AdministratorSportFacility> getAdministratorSportFacilities() 
	{
		return administratorSportFacilities;
	}

	public void setAdministratorSportFacilities(Set<AdministratorSportFacility> administratorSportFacilities) 
	{
		this.administratorSportFacilities = administratorSportFacilities;
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

	public Set<SportFacilityMember> getMembers() {
		return members;
	}


	public void setMembers(Set<SportFacilityMember> members)
	{
		this.members = members;
	}


	public String getUrlName() 
	{
		return urlName;
	}


	public void setUrlName(String urlName)
	{
		this.urlName = urlName;
	}


	public Set<Image> getImages()
	{
		return images;
	}


	public Set<Court> getCourts()
	{
		return courts;
	}


	public void setCourts(Set<Court> courts)
	{
		this.courts = courts;
	}


	public Set<Day> getDays() {
		return days;
	}


	public void setDays(Set<Day> days) {
		this.days = days;
	}


	public Set<SpecialDay> getSpecialDays() 
	{
		return specialDays;
	}


	public void setSpecialDays(Set<SpecialDay> specialDays) 
	{
		this.specialDays = specialDays;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}


	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	

}
