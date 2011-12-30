package es.tresw.db.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo 
{

	@Column(name="EMAIL", nullable=false, length=255,unique=true)
	private String email;
	@Column(name="PHONE1", nullable=false, length=255)
	private int telephone1;
	@Column(name="PHONE2", nullable=false, length=255)
	private int telephone2;

	public ContactInfo()
	{
		
	}
	
	public ContactInfo(String email, int telephone1, int telephone2) 
	{
		super();
		this.email = email;
		this.telephone1 = telephone1;
		this.telephone2 = telephone2;
	}

	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}

	public int getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(int telephone1) {
		this.telephone1 = telephone1;
	}

	public int getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(int telephone2) {
		this.telephone2 = telephone2;
	}
	

}
