package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.tresw.db.types.DayOfWeek;

@Entity
@Table(name="SCHEDULE",catalog="Alquisport")
public class Schedule 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	@Column(name="START_HOUR", length=2)
	private int startHour;
	@Column(name="END_HOUR", length=2)
	private int endHour;
	@Column(name="MINUTE_START", length=2)
	private int minStart;
	@Column(name="MINUTE_END", length=2)
	private int minEnd;
	@Column(name="PRICE_COURT", length=4)
	private float priceCourt;
	@Column(name="PRICE_LIGHT", length=4)
	private float priceLight;
	@ManyToOne
	@JoinColumn (name="COURT_ID")
	private Court court;
	
	public Schedule()
	{
		
	}

	public Schedule(Long id,DayOfWeek dayOfWeek, int startHour, int endHour,int minStart, int minEnd, float priceCourt, Court court, float priceLight) 
	{
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.startHour = startHour;
		this.endHour = endHour;
		this.minStart = minStart;
		this.minEnd = minEnd;
		this.priceCourt = priceCourt;
		this.priceLight=priceLight;
		this.court=court;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public DayOfWeek getDayOfWeek() 
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) 
	{
		this.dayOfWeek = dayOfWeek;
	}

	public int getStartHour() 
	{
		return startHour;
	}

	public void setStartHour(int startHour) 
	{
		this.startHour = startHour;
	}

	public int getEndHour() 
	{
		return endHour;
	}

	public void setEndHour(int endHour) 
	{
		this.endHour = endHour;
	}

	public int getMinStart() 
	{
		return minStart;
	}

	public void setMinStart(int minStart) 
	{
		this.minStart = minStart;
	}

	public int getMinEnd() 
	{
		return minEnd;
	}

	public void setMinEnd(int minEnd) 
	{
		this.minEnd = minEnd;
	}

	public float getPriceCourt() 
	{
		return priceCourt;
	}

	public void setPriceCourt(float priceCourt) 
	{
		this.priceCourt = priceCourt;
	}

	public float getPriceLight() 
	{
		return priceLight;
	}

	public void setPriceLight(float priceLight) 
	{
		this.priceCourt = priceLight;
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
