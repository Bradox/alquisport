package es.tresw.db.entities;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.Appearance;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@Table(name="SPORT_FACILITY",catalog="PISTEA")
public class SportFacility 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Pattern(regexp="[a-z]*",message="{identificador_incorrecto}")
	@Column(name="URL_NAME")
	private String urlName;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Column(name="NAME", length=255)
	private String name;
	
	@Column(name="GET_HERE", columnDefinition="TEXT")
	private String getHere;
	
	@Column(name="DESCRIPTION", columnDefinition="TEXT")
	private String description;
	
	@Column(name="STATE", length=2)
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
	private Set<Feature> features=new HashSet<Feature>();
	
	@OneToMany	
	@JoinTable(name = "SPORT_FACIlITY_IMAGE", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "IMAGE_ID") })
	private Set<Image> images=new HashSet<Image>();
	
	@OneToMany (mappedBy="sportFacility")
	private Set<Administrator> administrators=new HashSet<Administrator>();
	
	@OneToMany(mappedBy="sportFacility")	
	private Set<SportFacilityMember> members=new HashSet<SportFacilityMember>();
	
	@OneToMany(mappedBy="sportFacility")	
	private Set<Court> courts = new HashSet<Court>();
	
	public SportFacility()
	{
		
	}

	
	public SportFacility(Long id,String name, String getHere, String description, Integer state, Set<Feature> features, Set<Image> images, Set<Administrator> administrators, Address address, ContactInfo contactInfo, Appearance appearance, Set<SportFacilityMember> members, Set<Court> courts) 
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
		this.members=members;
		this.courts=courts;
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

	public Set<Administrator> getAdministrators() 
	{
		return administrators;
	}

	public void setAdministrators(Set<Administrator> administrators) 
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
	
	

}
