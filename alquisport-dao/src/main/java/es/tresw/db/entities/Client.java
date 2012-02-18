package es.tresw.db.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@Table(name="CLIENT",catalog="Alquisport")
public class Client extends User
{
	@OneToMany(mappedBy="client")	
	private List<SportFacilityMember> memberOf=new ArrayList<SportFacilityMember>();
	@OneToMany
	@JoinTable(name = "RENTAL_CLIENT", 
	     	   joinColumns = { @JoinColumn(name = "CLIENT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "RENTAL_ID") })
	private List<Rental> rentals=new ArrayList<Rental>();
	
	public Client()
	{
		super();
	}
	
	public Client(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Role> roles, Date birthDate, List<SportFacilityMember> memberOf, boolean enabled, List<Rental> rentals)
	{
		super(firstLastName, secondLastName, login, name, password, bankAccount, address, contactInfo, roles,birthDate, enabled);
		this.memberOf=memberOf;
		this.rentals=rentals;
	}

	public void setMemberOf(List<SportFacilityMember> memberOf)
	{
		this.memberOf=memberOf;
	}
	
	public List<SportFacilityMember> memberOf()
	{
		return this.memberOf;
	}

	public List<Rental> getRentals() 
	{
		return rentals;
	}

	public void setRentals(List<Rental> rentals)
	{
		this.rentals = rentals;
	}

	public List<SportFacilityMember> getMemberOf()
	{
		return memberOf;
	}
	
}
