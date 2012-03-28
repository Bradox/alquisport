package es.tresw.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.tresw.db.embeddable.CourtType;
import es.tresw.db.embeddable.ReservationConfig;

@Entity
@Table(name="COURT",catalog="PISTEA")
public class Court 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="DESCRIPTION", columnDefinition="TEXT")
	private String description;
	@Column(name="STATE", length=2)
	private int state;
	@Column(name="PRICE", length=4)
	private double price;
	@Embedded
	private CourtType courtType=new CourtType();
	@Embedded
	private ReservationConfig reservationConfig=new ReservationConfig();
	@OneToMany
	@JoinTable(name = "COURT_FEATURE", 
	     	   joinColumns = { @JoinColumn(name = "COURT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID") })
	private Set<Feature> features=new HashSet<Feature>();
	@OneToMany(mappedBy="court")
	private Set<Rental> rents=new HashSet<Rental>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_SPORT_FACILITY")
	public SportFacility sportFacility;
	@OneToMany
	@JoinTable(name = "COURT_DAY", 
	     	   joinColumns = { @JoinColumn(name = "COURT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "DAY_ID") })
	private Set<Day> days = new HashSet<Day>();
	@OneToMany
	@JoinTable(name = "COURT_SPECIAL_DAY", 
	     	   joinColumns = { @JoinColumn(name = "COURT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "DAY_ID") })
	private Set<SpecialDay> specialDays = new HashSet<SpecialDay>();
	@OneToMany
	@JoinColumn(name="COURT_ID")
	private Set<SpecialPrice> specialPrices=new HashSet<SpecialPrice>();
	
	public Court()
	{
		
	}
	
	public Court(String description, int state, CourtType courtType,ReservationConfig reservationConfig,Set<Feature> features, Set<Rental> rents, double price, SportFacility sportFacility, Set<Day> days, Set<SpecialDay> specialDays, Set<SpecialPrice> specialPrices) 
	{
		this.description = description;
		this.state = state;
		this.courtType = courtType;
		this.reservationConfig = reservationConfig;
		this.features = features;
		this.rents=rents;
		this.sportFacility = sportFacility;
		this.price = price;
		this.days=days;
		this.specialDays=specialDays;
		this.specialPrices=specialPrices;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public int getState() 
	{
		return state;
	}
	
	public void setState(int state)
	{
		this.state = state;
	}
	
	public CourtType getCourtType() 
	{
		return courtType;
	}
	
	public void setCourtType(CourtType courtType) 
	{
		this.courtType = courtType;
	}
	
	public ReservationConfig getReservationConfig() 
	{
		return reservationConfig;
	}
	
	public void setReservationConfig(ReservationConfig reservationConfig) 
	{
		this.reservationConfig = reservationConfig;
	}
	
	public Set<Feature> getFeatures() 
	{
		return features;
	}
	
	public void setFeatures(Set<Feature> features) 
	{
		this.features = features;
	}

	public Set<Rental> getRents()
	{
		return rents;
	}

	public void setRents(Set<Rental> rents)
	{
		this.rents = rents;
	}

	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility)
	{
		this.sportFacility = sportFacility;
	}

	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}

	public Set<Day> getDays() 
	{
		return days;
	}

	public void setDays(Set<Day> days) 
	{
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

	public Set<SpecialPrice> getSpecialPrices() 
	{
		return specialPrices;
	}

	public void setSpecialPrices(Set<SpecialPrice> specialPrices)
	{
		this.specialPrices = specialPrices;
	}
	
}
