package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="COUPON_USED",catalog="PISTEA")
public class CouponsUsed
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ID_COUPON")
	private Coupon coupon;
	@ManyToOne
	@JoinColumn(name = "ID_RENT")
	private Rental rent;
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private Client client;
	
	public CouponsUsed()
	{
		
	}
	
	public CouponsUsed(Long id,Coupon coupon, Rental rent, Client client) 
	{
		this.id = id;
		this.coupon = coupon;
		this.rent = rent;
		this.client = client;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Coupon getCoupon() 
	{
		return coupon;
	}
	
	public void setCoupon(Coupon coupon) 
	{
		this.coupon = coupon;
	}
	
	public Rental getRent() 
	{
		return rent;
	}
	
	public void setRent(Rental rent) 
	{
		this.rent = rent;
	}
	
	public Client getClient() 
	{
		return client;
	}
	
	public void setClient(Client client) 
	{
		this.client = client;
	}
	
}
