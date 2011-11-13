package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Embeddable
public class Appearance 
{

	@Column(name="COLOR_ONE", nullable=false, length=2)
	private String color1;
	@Column(name="COLOR_TWO", nullable=false, length=2)
	private String color2;
	@Column(name="COLOR_THREE", nullable=false, length=2)
	private String color3;
	@OneToOne
	@JoinColumn(name="IMAGE_ID")
	private Image logo;
	
	public Appearance()
	{
		
	}

	public Appearance(String color1, String color2, String color3, Image logo) 
	{
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.logo = logo;
	}

	public String getColor1() 
	{
		return color1;
	}

	public void setColor1(String color1) 
	{
		this.color1 = color1;
	}

	public String getColor2() 
	{
		return color2;
	}

	public void setColor2(String color2) 
	{
		this.color2 = color2;
	}

	public String getColor3() 
	{
		return color3;
	}

	public void setColor3(String color3) 
	{
		this.color3 = color3;
	}

	public Image getLogo() 
	{
		return logo;
	}

	public void setLogo(Image logo) 
	{
		this.logo = logo;
	}
	
}
