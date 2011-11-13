package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER", catalog="Alquisport")
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
	@Column(name="PRICE", length=4)
	private float price;
	
	public Schedule()
	{
		
	}

	public Schedule(Long id, DayOfWeek dayOfWeek, int startHour, int endHour,int minStart, int minEnd, float price) 
	{
		this.id = id;
		this.dayOfWeek = dayOfWeek;
		this.startHour = startHour;
		this.endHour = endHour;
		this.minStart = minStart;
		this.minEnd = minEnd;
		this.price = price;
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

	public float getPrice() 
	{
		return price;
	}

	public void setPrice(float price) 
	{
		this.price = price;
	}
	
}
