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

import es.tresw.db.embeddable.CourtType;
import es.tresw.db.embeddable.ReservationConfig;

@Entity
@Table(name="COURT",catalog="Alquisport")
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
	private CourtType courtType;
	@Embedded
	private ReservationConfig reservationConfig;
	@OneToMany
	@JoinTable(name = "COURT_FEATURE", 
	     	   joinColumns = { @JoinColumn(name = "COURT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID") })
	private List<Feature> features;
	@OneToMany(mappedBy="court")
	private List<Rental> rents;
	@OneToMany(mappedBy="court")	
	private List<Calendar> calendar;

	
	
	public Court()
	{
		
	}
	
	public Court(Long id,String description, int state, CourtType courtType,ReservationConfig reservationConfig,List<Feature> features, List<Rental> rents, List<Calendar> calendar) 
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
	
	public List<Feature> getFeatures() 
	{
		return features;
	}
	
	public void setFeatures(List<Feature> features) 
	{
		this.features = features;
	}

	public List<Rental> getRents()
	{
		return rents;
	}

	public void setRents(List<Rental> rents)
	{
		this.rents = rents;
	}

	public List<Calendar> getCalendar() {
		return calendar;
	}

	public void setCalendar(List<Calendar> calendar) {
		this.calendar = calendar;
	}

}
