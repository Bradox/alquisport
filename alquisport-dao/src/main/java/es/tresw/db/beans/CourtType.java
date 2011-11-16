package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourtType
{
	@Column(name="NAME", columnDefinition="TEXT")
	private String description;
	@Column(name="QUANTITY", length=3)
	private int quantity;
	
	public CourtType()
	{
		
	}

	public CourtType(String description, int quantity) 
	{
		this.description = description;
		this.quantity = quantity;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	public int getQuantity() 
	{
		return quantity;
	}

	public void setQuantity(int quantity) 
	{
		this.quantity = quantity;
	}

}
