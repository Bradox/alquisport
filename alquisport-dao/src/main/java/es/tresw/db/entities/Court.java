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
	@OneToMany(mappedBy="court")	
	private Set<Calendar> calendar=new HashSet<Calendar>();
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_SPORT_FACILITY")
	public SportFacility sportFacility;
	
	
	public Court()
	{
		
	}
	
	public Court(Long id,String description, int state, CourtType courtType,ReservationConfig reservationConfig,Set<Feature> features, Set<Rental> rents, Set<Calendar> calendar, SportFacility sportFacility) 
	{
		super();
		this.id = id;
		this.description = description;
		this.state = state;
		this.courtType = courtType;
		this.reservationConfig = reservationConfig;
		this.features = features;
		this.rents=rents;
		this.calendar=calendar;
		this.sportFacility = sportFacility;
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

	public Set<Calendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(Set<Calendar> calendar) {
		this.calendar = calendar;
	}

	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility)
	{
		this.sportFacility = sportFacility;
	}

	
}
