package es.tresw.db.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="USER_ID")
public class Administrator extends User
{
	@Embedded
	private Company company;
	@ManyToOne
	@JoinColumn(name = "ID_SPORTFACILITY")
	private SportFacility sportFacility;

	
	
	public Administrator(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Role> roles, Company company, SportFacility sportFacility, Date birthDate) 
	{
		super(firstLastName, secondLastName,login, name, password, bankAccount, address, contactInfo, roles, birthDate);
		this.company = company;
		this.sportFacility = sportFacility;
	}

	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}

	public void setSportFacility(SportFacility sportFacility) 
	{
		this.sportFacility = sportFacility;
	}

	public Company getCompany() 
	{
		return company;
	}

	public void setCompany(Company company)
	{
		this.company = company;
	}
	
}
