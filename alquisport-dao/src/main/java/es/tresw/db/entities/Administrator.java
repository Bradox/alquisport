package es.tresw.db.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.Company;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@PrimaryKeyJoinColumn(name="USER_ID")
@Table(name="ADMINISTRATOR", catalog="Alquisport")
public class Administrator extends User
{
	@Embedded
	private Company company;
	@ManyToOne
	@JoinColumn(name = "ID_SPORTFACILITY")
	private SportFacility sportFacility;

	public Administrator()
	{
		
	}
	
	public Administrator(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Role> roles, Company company, SportFacility sportFacility, Date birthDate, Boolean enabled) 
	{
		super(firstLastName, secondLastName,login, name, password, bankAccount, address, contactInfo, roles, birthDate, enabled);
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
