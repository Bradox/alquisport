package es.tresw.db.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="USER", catalog="Alquisport")
@Inheritance(strategy=InheritanceType.JOINED)
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Size(max=255, message="El apellido no puede superar los 255 caracteres")
	@Column(name="firstLastName", nullable=false, length=255)
	private String firstLastName;
	@Size(max=255, message="El apellido no puede superar los 255 caracteres")
	@Column(name="secondLastName", nullable=false, length=255)
	private String secondLastName;
	@Size(min=6,max=255, message="El login debe ser mayor de 6 caract�res")
    @Column(name="LOGIN", nullable=false, length=255)
	private String login;
	@Size(max=255, message="El nombre no puede superar los 255 caracteres")
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Size(min=6,max=255, message="La contrasena debe ser mayor de 6 caracteres")
	@Column(name="PASSWORD", nullable=false, length=255)
	private String password;
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	@Column(name="ENABLED",columnDefinition="bool default true")
	private Boolean enabled;
    @OneToMany(mappedBy="user")
	private List<Authority> authorities;
    @OneToMany (mappedBy="userTo")
    public List<Message> messagesTo;
    @OneToMany(mappedBy="userFrom")
    public List<Message> messagesFrom;
	@Embedded
	private BankAccount bankAccount;
	@Embedded
	private Address address;
	@Embedded
	private ContactInfo contactInfo;
	
	public User()
	{
		
	}
	
	public User(String firstLastName, String secondLastName, String login, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, List<Authority> authorities, Date birthDate, Boolean enabled) 
	{
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.login = login;
		this.name = name;
		this.password = password;
		this.bankAccount = bankAccount;
		this.address = address;
		this.contactInfo = contactInfo;
		this.authorities=authorities;
		this.birthDate=birthDate;
		this.enabled=enabled;
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

	public List<Authority> getAuthorities() 
	{
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) 
	{
		this.authorities = authorities;
	}

	public Date getBirthDate() 
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate) 
	{
		this.birthDate = birthDate;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

	public List<Message> getMessagesTo()
	{
		return messagesTo;
	}

	public void setMessagesTo(List<Message> messagesTo)
	{
		this.messagesTo = messagesTo;
	}

	public List<Message> getMessagesFrom()
	{
		return messagesFrom;
	}

	public void setMessagesFrom(List<Message> messagesFrom)
	{
		this.messagesFrom = messagesFrom;
	}
		
	
}
