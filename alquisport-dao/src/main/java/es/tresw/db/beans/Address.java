package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address 
{
	@Column(name="ADDRESS", nullable=false, length=255)
	public String address;
	@Column(name="TYPE", nullable=false, length=255)
	public String type;
	@Column(name="PROVINCE", nullable=false, length=255)
	public String province;
	@Column(name="ZONE", nullable=false, length=255)
	public String zone;
	
	public Address()
	{
		
	}
	
	public Address(String address, String type, String province, String zone) 
	{
		super();
		this.address = address;
		this.type = type;
		this.province = province;
		this.zone = zone;
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
	
	public String getProvince() 
	{
		return province;
	}
	
	public void setProvince(String province) 
	{
		this.province = province;
	}
	
	public String getZone() 
	{
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	
}
