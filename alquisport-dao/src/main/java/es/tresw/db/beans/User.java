package es.tresw.db.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;


@Entity
@Table(name="USER", catalog="Alquisport")
@Inheritance(strategy=InheritanceType.JOINED)
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="firstLastName", nullable=false, length=255)
	private String firstLastName;
	@Column(name="secondLastName", nullable=false, length=255)
	private String secondLastName;
    @Column(name="LOGIN", nullable=false, length=255)
	private String login;
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Column(name="PASSWORD", nullable=false, length=255)
	private String password;
    @OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", 
	 		   joinColumns = { @JoinColumn(name = "USER_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	private List<Role> roles;
	@Embedded
	private BankAccount bankAccount;
	@Embedded
	private Address address;
	@Embedded
	private ContactInfo contactInfo;
	
	public User()
	{
		
	}
	
	public User(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Role> roles) 
	{
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.login = login;
		this.name = name;
		this.password = password;
		this.bankAccount = bankAccount;
		this.address = address;
		this.contactInfo = contactInfo;
		this.roles=roles;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getFirstLastName() 
	{
		return firstLastName;
	}
	
	public void setFirstLastName(String firstLastName) 
	{
		this.firstLastName = firstLastName;
	}
	
	public String getSecondLastName() 
	{
		return secondLastName;
	}
	
	public void setSecondLastName(String secondLastName) 
	{
		this.secondLastName = secondLastName;
	}
	
	public String getLogin() 
	{
		return login;
	}
	
	public void setLogin(String login) 
	{
		this.login = login;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public BankAccount getBankAccount() 
	{
		if(bankAccount==null)
			bankAccount=new BankAccount();
		return bankAccount;
	}
	
	public void setBankAccount(BankAccount bankAccount) 
	{
		this.bankAccount = bankAccount;
	}
	
	public Address getAddress() 
	{
		if(address==null)
			address=new Address();
		return address;
	}
	
	public void setAddress(Address address) 
	{
		this.address = address;
	}
	
	public ContactInfo getContactInfo() 
	{
		if(contactInfo==null)
			contactInfo=new ContactInfo();
		return contactInfo;
	}
	
	public void setContactInfo(ContactInfo contactInfo) 
	{
		this.contactInfo = contactInfo;
	}

	public List<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(List<Role> roles) 
	{
		this.roles = roles;
	}
	
}
