package es.tresw.db.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;

@Embeddable
public class ContactInfo 
{

	@Size(min=1,max=255,message="{campo_obligatorio}")
	@Email(message="{email_incorrecto}")
	@Column(name="EMAIL", length=255,unique=true)
	private String email;
	
	@Size(max=15,message="{telefono_incorrecto}")
	@Column(name="PHONE1", length=255)
	private String telephone1;
	
	@Size(max=15,message="{telefono_incorrecto}")
	@Column(name="PHONE2", length=255)
	private String telephone2;

	public ContactInfo()
	{
		
	}
	
	public ContactInfo(String email, String telephone1, String telephone2) 
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

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}
	

}
