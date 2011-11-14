package es.tresw.db.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="USER_ID")
public class Client extends User
{
	@OneToMany(mappedBy="user")	
	private List<Member> memberOf;
	
	public Client()
	{
		super();
	}
	
	public Client(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Authority> authorities, Date birthDate, List<Member> memberOf, boolean enabled)
	{
		super(firstLastName, secondLastName, login, name, password, bankAccount, address, contactInfo, authorities,birthDate, enabled);
		this.memberOf=memberOf;
	}

	public void setMemberOf(List<Member> memberOf)
	{
		this.memberOf=memberOf;
	}
	
	public List<Member> memberOf()
	{
		return this.memberOf;
	}
}
