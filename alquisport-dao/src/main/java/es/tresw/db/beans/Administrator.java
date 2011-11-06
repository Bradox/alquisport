package es.tresw.db.beans;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="userId")
public class Administrator extends User
{
	@ManyToOne
	@JoinColumn(name = "ID_SPORTFACILITY")
	private SportFacility sportFacility;

	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) 
	{
		this.sportFacility = sportFacility;
	}
	
}
