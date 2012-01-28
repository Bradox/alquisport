package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUPON")
public class Coupon 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="DISCOUNT_QUANTITY", length=2, nullable=false)
	private float discountQuantity;
	@Column(name="CODE", nullable=false, length=2)
	private String code;
	@Column(name="TIMES_USED", nullable=false, length=3)
	private int timesUsed;
	@Column(name="MAX_TIMES_USE", nullable=false, length=3)
	private int maxTimesUse;
	@Column(name="DISCOUNT_TYPE", nullable=false, length=2)
	private int discountType;
	
	public Coupon()
	{
		
	}
	
	public Coupon(Long id,float discountQuantity, String code, int timesUsed,int maxTimesUse, int discountType) 
	{
		this.id = id;
		this.discountQuantity = discountQuantity;
		this.code = code;
		this.timesUsed = timesUsed;
		this.maxTimesUse = maxTimesUse;
		this.discountType = discountType;
	}
	
	public Long getId() 
	{
		return id;
	}
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public float getDiscountQuantity() 
	{
		return discountQuantity;
	}
	
	public void setDiscountQuantity(float discountQuantity) 
	{
		this.discountQuantity = discountQuantity;
	}
	public String getCode() 
	{
		return code;
	}
	
	public void setCode(String code) 
	{
		this.code = code;
	}
	
	public int getTimesUsed() 
	{
		return timesUsed;
	}
	
	public void setTimesUsed(int timesUsed) 
	{
		this.timesUsed = timesUsed;
	}
	
	public int getMaxTimesUse() 
	{
		return maxTimesUse;
	}
	
	public void setMaxTimesUse(int maxTimesUse) 
	{
		this.maxTimesUse = maxTimesUse;
	}
	
	public int getDiscountType() 
	{
		return discountType;
	}
	
	public void setDiscountType(int discountType) 
	{
		this.discountType = discountType;
	}
	
}
