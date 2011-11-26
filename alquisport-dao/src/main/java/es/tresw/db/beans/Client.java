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
	private List<SportFacilityMember> memberOf;
	
	public Client()
	{
		super();
	}
	
	public Client(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Authority> authorities, Date birthDate, List<SportFacilityMember> memberOf, boolean enabled)
	{
		super(firstLastName, secondLastName, login, name, password, bankAccount, address, contactInfo, authorities,birthDate, enabled);
		this.memberOf=memberOf;
	}

	public void setMemberOf(List<SportFacilityMember> memberOf)
	{
		this.memberOf=memberOf;
	}
	
	public List<SportFacilityMember> memberOf()
	{
		return this.memberOf;
	}

	@Override
	public String toString() {
		return "Client [memberOf=" + memberOf + ", getId()=" + getId()
				+ ", getFirstLastName()=" + getFirstLastName()
				+ ", getSecondLastName()=" + getSecondLastName()
				+ ", getLogin()=" + getLogin() + ", getName()=" + getName()
				+ ", getPassword()=" + getPassword() + ", getBankAccount()="
				+ getBankAccount() + ", getAddress()=" + getAddress()
				+ ", getContactInfo()=" + getContactInfo()
				+ ", getAuthorities()=" + getAuthorities()
				+ ", getBirthDate()=" + getBirthDate() + ", getEnabled()="
				+ getEnabled() + ", getMessagesTo()=" + getMessagesTo()
				+ ", getMessagesFrom()=" + getMessagesFrom() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
