package es.tresw.db.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COURT", catalog="Alquisport")
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
	@OneToMany(mappedBy="court")
	private Schedule schedule;
	@Embedded
	private ReservationConfig reservationConfig;
	@OneToMany(mappedBy="court")
	private List<Feature> features;
	@OneToMany(mappedBy="court")
	private List<Rent> rents;
	
	
	public Court()
	{
		
	}
	
	public Court(Long id, String description, int state, CourtType courtType,Schedule schedule, ReservationConfig reservationConfig,List<Feature> features, List<Rent> rents) 
	{
		super();
		this.id = id;
		this.description = description;
		this.state = state;
		this.courtType = courtType;
		this.schedule = schedule;
		this.reservationConfig = reservationConfig;
		this.features = features;
		this.setRents(rents);
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
	
	public Schedule getSchedule() 
	{
		return schedule;
	}
	
	public void setSchedule(Schedule schedule) 
	{
		this.schedule = schedule;
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

	public List<Rent> getRents()
	{
		return rents;
	}

	public void setRents(List<Rent> rents)
	{
		this.rents = rents;
	}

}
