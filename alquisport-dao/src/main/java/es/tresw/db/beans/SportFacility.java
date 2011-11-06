package es.tresw.db.beans;

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

@Entity
@Table(name="SPORT_FACILITY", catalog="Alquisport")
public class SportFacility 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Column(name="NAME", columnDefinition="TEXT")
	private String getHere;
	@Column(name="NAME", columnDefinition="TEXT")
	private String description;
	@Column(name="NAME", nullable=false, length=2)
	private Integer state;
	@JoinTable(name = "SPORT_FACILITY_FEATURE", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID") })
	private List<Feature> features;
	@OneToMany
	@JoinTable(name = "SPORT_FACILITY_IMAGE", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "IMAGE_ID") })
	private List<Image> imagenes;
	@OneToMany (mappedBy="sportFacility")
	@JoinColumn(name = "ID_SPORTFACILITY")
	private List<Administrator> administrators;
	@Embedded
	private Address address;
	@Embedded
	private ContactInfo contactInfo;
	@Embedded
	private Appearance appearance;
	
	public SportFacility()
	{
		
	}

	
	public SportFacility(Long id, String name, String getHere, String description, Integer state, List<Feature> features, List<Image> imagenes, List<Administrator> administrators, Address address, ContactInfo contactInfo, Appearance appearance) 
	{
		this.id = id;
		this.name = name;
		this.getHere = getHere;
		this.description = description;
		this.state = state;
		this.features = features;
		this.imagenes = imagenes;
		this.administrators = administrators;
		this.address = address;
		this.contactInfo = contactInfo;
		this.appearance = appearance;
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
		return imagenes;
	}

	public void setImagenes(List<Image> imagenes) 
	{
		this.imagenes = imagenes;
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

}
