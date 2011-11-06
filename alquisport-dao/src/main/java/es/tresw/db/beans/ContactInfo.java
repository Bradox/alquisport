package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo 
{

	@Column(name="EMAIL", nullable=false, length=255)
	private String email;
	@Column(name="PHONE", nullable=false, length=255)
	private String telephone;
	@Column(name="CELLPHONE", nullable=false, length=255)
	private String cellphone;

	public ContactInfo()
	{
		
	}
	
	public ContactInfo(String email, String telephone, String cellphone) 
	{
		super();
		this.email = email;
		this.telephone = telephone;
		this.cellphone = cellphone;
	}

	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getTelephone() 
	{
		return telephone;
	}
	
	public void setTelephone(String telephone) 
	{
		this.telephone = telephone;
	}
	
	public String getCellphone() 
	{
		return cellphone;
	}
	
	public void setCellphone(String cellphone) 
	{
		this.cellphone = cellphone;
	}

}
