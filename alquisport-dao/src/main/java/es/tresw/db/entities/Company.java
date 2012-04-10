package es.tresw.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.tresw.db.embeddable.Address;


@Entity
@Table(name="COMPANY")
public class Company 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Column(name="CIF", nullable=false, length=9)
	private String CIF;
	@Embedded
	private Address address;
	@OneToMany(mappedBy="company")
	private Set<Administrator> administrators=new HashSet<Administrator>();
	
	public Company()
	{
		
	}	
	
	public Company(String name, String CIF, Address address, Set<Administrator> administrators)
	{
		this.name=name;
		this.CIF=CIF;
		this.address=address;
		this.administrators = administrators;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getCIF() 
	{
		return CIF;
	}
	
	public void setCIF(String CIF) 
	{
		this.CIF = CIF;
	}
	
	public Address getAddress() 
	{
		return address;
	}
	
	public void setAddress(Address address) 
	{
		this.address = address;
	}

	public Set<Administrator> getAdministrators() 
	{
		return administrators;
	}

	public void setAdministrators(Set<Administrator> administrators)
	{
		this.administrators = administrators;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	
}
