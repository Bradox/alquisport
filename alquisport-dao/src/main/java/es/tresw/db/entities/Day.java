package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.tresw.db.types.DayOfWeek;

@Entity
@Table(name="DAY")
@DiscriminatorColumn(name="DAYTYPE",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("NORMAL")
public class Day 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="DAY", length=2)
	private int day;
	@Column(name="DAY_OF_WEEK")
	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;
	@OneToOne
	@JoinColumn(name = "ID_SCHEDULE")
	private Schedule schedule;

	public Day()
	{
		
	}
	
	public Day(int year, int day, Schedule schedule)
	{
		this.day = day;
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
