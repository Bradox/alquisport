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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.tresw.db.types.DayOfWeek;

@Entity
@Table(name="DAYS",catalog="PISTEA")
public class Day 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="DAY", length=2)
	private int day;
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	@ManyToOne
	@JoinColumn (name="ID_MONTH")
	private Month month;
	@OneToOne
	@JoinColumn(name = "ID_SCHEDULE")
	private Schedule schedule;

	public Day()
	{
		
	}
	
	public Day(Long id,int year, int day, Month month, Schedule schedule)
	{
		this.id = id;
		this.day = day;
		this.month = month;
		this.schedule = schedule;
		dayOfWeek=DayOfWeek.fromCalendarDay(day);
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public int getDay() 
	{
		return day;
	}
	
	public void setDay(int day) 
	{
		this.day = day;
	}
	
	public Month getMonth() 
	{
		return month;
	}
	
	public void setMonth(Month month) 
	{
		this.month = month;
	}

	public DayOfWeek getDayOfWeek()
	{
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek)
	{
		this.dayOfWeek = dayOfWeek;
	}

	public Schedule getSchedule()
	{
		return schedule;
	}

	public void setSchedule(Schedule schedule)
	{
		this.schedule = schedule;
	}
	
	
}
