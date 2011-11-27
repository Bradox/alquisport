package es.tresw.db.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo 
{

	@Column(name="EMAIL", nullable=false, length=255,unique=true)
	private String email;
	@Column(name="PHONE", nullable=false, length=255)
	private int telephone;
	@Column(name="CELLPHONE", nullable=false, length=255)
	private int cellphone;

	public ContactInfo()
	{
		
	}
	
	public ContactInfo(String email, int telephone, int cellphone) 
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
	
	public int getTelephone() 
	{
		return telephone;
	}
	
	public void setTelephone(int telephone) 
	{
		this.telephone = telephone;
	}
	
	public int getCellphone() 
	{
		return cellphone;
	}
	
	public void setCellphone(int cellphone) 
	{
		this.cellphone = cellphone;
	}

}
