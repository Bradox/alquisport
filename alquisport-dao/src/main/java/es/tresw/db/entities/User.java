package es.tresw.db.entities;

import java.util.HashSet;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Type;
import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;


@Entity
@Table(name="USER",catalog="PISTEA")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="USERTYPE",discriminatorType=DiscriminatorType.STRING)
public class User 
{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Column(name="FIRST_LASTNAME", nullable=false, length=255)
	private String firstLastName;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Column(name="SECOND_LASTNAME", nullable=false, length=255)
	private String secondLastName;
		
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
    @Column(name="username", nullable=false, length=255)
	private String username;
	
	@NotNull
	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	
	@Size(min=5,max=255,message="{password_incorrecto}")
	@Column(name="PASSWORD", nullable=false, length=255)
	private String password;
	
	@Column(name="BIRTH_DATE")
	private Date birthDate;
	
	@Column(name="ENABLED")
	@Type(type="true_false")
	private boolean enabled;
    
    @OneToMany (mappedBy="userTo")
    private Set<Message> messagesTo=new HashSet<Message>();
    
    @ManyToOne
    @JoinColumn(name="ROLE_ID")
    private UserRole roles;
    
    @OneToMany(mappedBy="userFrom")
    private Set<Message> messagesFrom=new HashSet<Message>();
    
	@Embedded
	private BankAccount bankAccount;
	
	@Embedded
	private Address address;
	
	@Embedded
	private ContactInfo contactInfo;
	
	public User()
	{
		
	}
	
	public User(String firstLastName, String secondLastName, String username, String name, String password, BankAccount bankAccount, Address address, ContactInfo contactInfo, UserRole roles, Date birthDate, Boolean enabled) 
	{
		this.firstLastName = firstLastName;
		this.secondLastName = secondLastName;
		this.username = username;
		this.name = name;
		this.password = password;
		this.bankAccount = bankAccount;
		this.address = address;
		this.contactInfo = contactInfo;
		this.roles=roles;
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
	
	public String getusername() 
	{
		return username;
	}
	
	public void setusername(String username) 
	{
		this.username = username;
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

	public UserRole getRoles() 
	{
		return roles;
	}

	public void setRoles(UserRole roles) 
	{
		this.roles = roles;
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

	public Set<Message> getMessagesTo()
	{
		return messagesTo;
	}

	public void setMessagesTo(Set<Message> messagesTo)
	{
		this.messagesTo = messagesTo;
	}

	public Set<Message> getMessagesFrom()
	{
		return messagesFrom;
	}

	public void setMessagesFrom(Set<Message> messagesFrom)
	{
		this.messagesFrom = messagesFrom;
	}


}
