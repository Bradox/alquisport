package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="SCHEDULE")
public class Schedule 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="START_HOUR", length=2)
	private int startHour;
	@Column(name="END_HOUR", length=2)
	private int endHour;
	@Column(name="MINUTE_START", length=2)
	private int minStart;
	@Column(name="MINUTE_END", length=2)
	private int minEnd;
	
	public Schedule()
	{
		
	}

	public Schedule(int startHour, int endHour,int minStart, int minEnd, float priceCourt, float priceLight) 
	{
		this.startHour = startHour;
		this.endHour = endHour;
		this.minStart = minStart;
		this.minEnd = minEnd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
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

}
