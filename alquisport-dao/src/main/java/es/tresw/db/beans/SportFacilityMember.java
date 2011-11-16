package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private User user;
	
	public SportFacilityMember()
	{
		
	}
	
	
	
	public SportFacilityMember(Long id, SportFacility sportFacility, User user)
	{
		this.id = id;
		this.sportFacility = sportFacility;
		this.user = user;
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
	
	public User getUser() 
	{
		return user;
	}
	
	public void setUser(User user) 
	{
		this.user = user;
	}
}