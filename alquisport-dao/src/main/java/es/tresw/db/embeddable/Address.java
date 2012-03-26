package es.tresw.db.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.Zone;

@Embeddable
public class Address 
{
	@Column(name="ADDRESS", length=255)
	public String address;
	
	@Column(name="TYPE", length=255)
	public String type;
	
	@Size(min=5,max=5,message="{zipcode_incorrecto}")
	@Column(name="ZIP_CODE", length=255)
	public String zipCode;
	
	@ManyToOne
	@JoinColumn(name = "ID_PROVINCE")
	public Province province;
	
	@ManyToOne
	@JoinColumn(name = "ID_ZONE")
	public Zone zone;
	
	@ManyToOne
	@JoinColumn(name = "ID_MUNICIPALITY")
	public Municipality municipality;
	
	
	public Address()
	{
		
	}
	
	public Address(String address, String type, Province province, Zone zone, Municipality municipality) 
	{
		super();
		this.address = address;
		this.type = type;
		this.province = province;
		this.zone = zone;
		this.municipality=municipality;
	}

	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}
	
	public String getType() 
	{
		return type;
	}
	
	public void setType(String type) 
	{
		this.type = type;
	}
	
	public Province getProvince() 
	{
		return province;
	}
	
	public void setProvince(Province province) 
	{
		this.province = province;
	}
	
	public Zone getZone() 
	{
		return zone;
	}

	public void setZone(Zone zone)
	{
		this.zone = zone;
	}

	public Municipality getMunicipality() 
	{
		return municipality;
	}

	public void setMunicipality(Municipality municipality)
	{
		this.municipality = municipality;
	}

	public void setZipCode(String zipCode)
	{
		this.zipCode=zipCode;
	}
	
	public String getZipCode()
	{
		return zipCode;
	}
}
