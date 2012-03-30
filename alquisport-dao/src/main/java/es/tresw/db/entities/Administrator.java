package es.tresw.db.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@Table(name="ADMINISTRATOR"/*, catalog="PISTEA"*/)
@DiscriminatorValue("ADMIN")
public class Administrator extends User
{
	@ManyToOne
	@JoinColumn(name = "ID_COMPANY")
	private Company company;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "administrator")
    private Set<AdministratorSportFacility> administratorSportFacilities = new HashSet<AdministratorSportFacility>(0);

	public Administrator()
	{
		
	}
	
	public Administrator(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, UserRole roles, Company company, Set<AdministratorSportFacility> administratorSportFacilities, Date birthDate, Boolean enabled) 
	{
		super(firstLastName, secondLastName,login, name, password, bankAccount, address, contactInfo, roles, birthDate, enabled);
		this.company = company;
		this.administratorSportFacilities = administratorSportFacilities;
	}

	public Set<AdministratorSportFacility>  getAdministratorSportFacilities() 
	{
		return administratorSportFacilities;
	}

	public void setSportFacility(Set<AdministratorSportFacility> administratorSportFacilities) 
	{
		this.administratorSportFacilities = administratorSportFacilities;
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
