package es.tresw.db.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIAL_PRICE")
public class SpecialPrice 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="DESCRIPTION", columnDefinition="TEXT")
	private String description;
	@Column(name="START_HOUR", length=2)
	private int startHour;
	@Column(name="END_HOUR", length=2)
	private int endHour;
	@Column(name="MINUTE_START", length=2)
	private int minStart;
	@Column(name="MINUTE_END", length=2)
	private int minEnd;
	@Column(name="DATE_START")
	public Date dateStart;
	@Column(name="DATE_FINISH")
	public Date dateFinish;
	
	public SpecialPrice()
	{
		
	}

	
	public SpecialPrice(String description, int startHour, int endHour,int minStart, int minEnd, Date dateStart, Date dateFinish) 
	{
		this.description = description;
		this.startHour = startHour;
		this.endHour = endHour;
		this.minStart = minStart;
		this.minEnd = minEnd;
		this.dateStart = dateStart;
		this.dateFinish = dateFinish;
	}


	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
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

	public Date getDateStart() 
	{
		return dateStart;
	}

	public void setDateStart(Date dateStart) 
	{
		this.dateStart = dateStart;
	}

	public Date getDateFinish() 
	{
		return dateFinish;
	}

	public void setDateFinish(Date dateFinish) 
	{
		this.dateFinish = dateFinish;
	}
	
}
