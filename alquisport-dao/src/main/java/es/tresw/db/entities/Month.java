package es.tresw.db.entities;


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.tresw.db.types.MonthName;

@Entity
@Table(name="MONTH",catalog="PISTEA")
public class Month 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="MONTH")
	private int month;
	@Enumerated(EnumType.STRING)
	private MonthName monthName;
	@ManyToOne
	@JoinColumn(name = "ID_YEAR")
	private Year year;
	@OneToMany(mappedBy="month")	
	private Set<Day> days=new HashSet<Day>();
	
	public Month()
	{
		
	}
	
	
	
	public Month(int month, Year year, Set<Day> days)
	{
		super();
		this.month = month;
		this.year = year;
		this.days = days;
		this.monthName=MonthName.fromCalendarMonth(month);
	}



	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id)
	{
		this.id = id;
	}
	
	public int getMonth()
	{
		return month;
	}
	
	public void setMonth(int month) 
	{
		this.month = month;
	}
	
	public MonthName getMonthName() 
	{
		return monthName;
	}
	
	public void setMonthName(MonthName monthName) 
	{
		this.monthName = monthName;
	}
	
	public Year getYear() 
	{
		return year;
	}
	
	public void setYear(Year year) 
	{
		this.year = year;
	}
	
	public Set<Day> getDays()
	{
		return days;
	}

	public void setDays(Set<Day> days) 
	{
		this.days = days;
	}
	
	
	
}
