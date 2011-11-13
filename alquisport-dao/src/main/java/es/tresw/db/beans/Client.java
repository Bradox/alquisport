package es.tresw.db.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="USER_ID")
public class Client extends User
{
	@Column(name="MEMBER",columnDefinition="bool default false")
	private boolean isMember;

	public Client()
	{
		super();
	}
	
	public Client(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Role> roles, Date birthDate, boolean isMember)
	{
		super(firstLastName, secondLastName, login, name, password, bankAccount, address, contactInfo, roles,birthDate);
		this.isMember=isMember;
	}
	public boolean isMember() 
	{
		return isMember;
	}

	public void setMember(boolean isMember) 
	{
		this.isMember = isMember;
	}
	
}
