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
@Table(name="DAYS_CLOSED", catalog="Alquisport")
public class DayClosed 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="YEAR", length=4)
	private int year;
	@Column(name="DAY", length=2)
	private int day;
	@Column(name="MONTH", length=2)
	private int month;
	@ManyToOne
	@JoinColumn (name="SPORT_FACILITY_ID")
	private SportFacility sportFacility;

	public DayClosed()
	{
		
	}
	
	public DayClosed(Long id, int year, int day, int month,SportFacility sportFacility)
	{
		this.id = id;
		this.year = year;
		this.day = day;
		this.month = month;
		this.sportFacility = sportFacility;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public int getYear() 
	{
		return year;
	}
	
	public void setYear(int year) 
	{
		this.year = year;
	}
	
	public int getDay() 
	{
		return day;
	}
	
	public void setDay(int day) 
	{
		this.day = day;
	}
	
	public int getMonth() 
	{
		return month;
	}
	
	public void setMonth(int month) 
	{
		this.month = month;
	}
	
	public SportFacility getSportFacility() 
	{
		return sportFacility;
	}
	
	public void setSportFacility(SportFacility sportFacility) 
	{
		this.sportFacility = sportFacility;
	}
	
}
