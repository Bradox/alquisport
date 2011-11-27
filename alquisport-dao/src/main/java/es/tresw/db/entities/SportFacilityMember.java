package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="SPORT_FACILITY_MEMBER", catalog="Alquisport")
public class SportFacilityMember 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ID_SPORTFACILITY")
	private SportFacility sportFacility;
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private Client client;
	
	public SportFacilityMember()
	{
		
	}
	
	
	
	public SportFacilityMember(Long id, SportFacility sportFacility, Client client)
	{
		this.id = id;
		this.sportFacility = sportFacility;
		this.client = client;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}
	
	public void setSportFacility(SportFacility sportFacility) 
	{
		this.sportFacility = sportFacility;
	}
	
	public Client getClient() 
	{
		return client;
	}
	
	public void setClient(Client client) 
	{
		this.client = client;
	}
}
