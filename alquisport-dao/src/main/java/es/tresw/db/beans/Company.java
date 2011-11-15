package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class Company 
{
	@Column(name="NAME", nullable=false, length=255)
	private String name;
	@Column(name="CIF", nullable=false, length=9)
	private String CIF;
	@Embedded
	private Address address;
	
	public Company()
	{
		
	}	
	
	public Company(String name, String CIF, Address address)
	{
		this.name=name;
		this.CIF=CIF;
		this.address=address;
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

}
