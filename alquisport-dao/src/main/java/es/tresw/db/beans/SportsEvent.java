package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="SPORTS_EVENT", catalog="Alquisport")
public class SportsEvent 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Column(name="NAME", columnDefinition="TEXT")
	private String description;
	@ManyToOne
	@JoinColumn(name = "ID_SPORTFACILITY")
	private SportFacility sportFacility;
	@OneToMany(mappedBy="event")
	private Rental rents;
	
	public SportsEvent()
	{
		
	}

	public SportsEvent(Long id, String name, String description, SportFacility sportFacility, Rental rents) 
	{
		this.id = id;
		this.name = name;
		this.description = description;
		this.sportFacility = sportFacility;
		this.rents = rents;
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

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) 
	{
		this.sportFacility = sportFacility;
	}

	public Rental getRents() 
	{
		return rents;
	}

	public void setRents(Rental rents) 
	{
		this.rents = rents;
	}

}