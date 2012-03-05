package es.tresw.db.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="RENT",catalog="PISTEA")
public class Rental
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="PAYMENT_STATE", length=2)
	private int paymentState;
	@Column(name="QUANTITY_PAID", length=2)
	private int quantityPaid;
	@Column(name="DATE_START")
	private Date dateStart;
	@Column(name="DATE_END")
	private Date dateEnd;
	@ManyToOne
	@JoinColumn(name="COURT_ID")	
	private Court court;

	public Rental()
	{
		
	}

	public Rental(Long id,int paymentState, int quantityPaid, Court court, Date dateStart, Date dateEnd) 
	{
		this.id = id;
		this.paymentState = paymentState;
		this.quantityPaid = quantityPaid;
		this.court=court;
		this.dateEnd=dateEnd;
		this.dateStart=dateStart;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public int getPaymentState() 
	{
		return paymentState;
	}

	public void setPaymentState(int paymentState) 
	{
		this.paymentState = paymentState;
	}

	public int getQuantityPaid() 
	{
		return quantityPaid;
	}

	public void setQuantityPaid(int quantityPaid) 
	{
		this.quantityPaid = quantityPaid;
	}

	public Date getDateStart() 
	{
		return dateStart;
	}

	public void setDateStart(Date dateStart) 
	{
		this.dateStart = dateStart;
	}

	public Date getDateEnd() 
	{
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) 
	{
		this.dateEnd = dateEnd;
	}

	public Court getCourt() 
	{
		return court;
	}

	public void setCourt(Court court) 
	{
		this.court = court;
	}

}
