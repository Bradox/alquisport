package es.tresw.db.entities;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;

@Entity
@Table(name="CLIENT")
@DiscriminatorValue("CLIENT")
public class Client extends User
{
	@OneToMany(mappedBy="client")	
	private Set<SportFacilityMember> memberOf=new HashSet<SportFacilityMember>();
	@OneToMany
	@JoinTable(name = "RENTAL_CLIENT", 
	     	   joinColumns = { @JoinColumn(name = "CLIENT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "RENTAL_ID") })
	private Set<Rental> rentals=new HashSet<Rental>();
	
	public Client()
	{
		super();
	}
	
	public Client(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, UserRole roles, Date birthDate, Set<SportFacilityMember> memberOf, boolean enabled, Set<Rental> rentals)
	{
		super(firstLastName, secondLastName, login, name, password, bankAccount, address, contactInfo, roles,birthDate, enabled);
		this.memberOf=memberOf;
		this.rentals=rentals;
	}

	public void setMemberOf(Set<SportFacilityMember> memberOf)
	{
		this.memberOf=memberOf;
	}
	
	public Set<SportFacilityMember> memberOf()
	{
		return this.memberOf;
	}

	public Set<Rental> getRentals() 
	{
		return rentals;
	}

	public void setRentals(Set<Rental> rentals)
	{
		this.rentals = rentals;
	}

	public Set<SportFacilityMember> getMemberOf()
	{
		return memberOf;
	}
	
}
