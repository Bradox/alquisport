package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import es.tresw.db.types.MonthName;

@Entity
@Table(name="SPECIAL_DAY")
@DiscriminatorColumn(name="DAYTYPE",discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue("SPECIAL")
public class SpecialDay extends Day
{
	@Column(name="MONTH_NAME")
	@Enumerated(EnumType.STRING)
	private MonthName monthName;

	public SpecialDay()
	{
		
	}
	
	public SpecialDay(int year, int day, Schedule schedule, MonthName monthName)
	{
		super(year, day, schedule);
		this.monthName=monthName;
	}

	public MonthName getMonthName() 
	{
		return monthName;
	}

	public void setMonthName(MonthName monthName)
	{
		this.monthName = monthName;
	}
	
	
}
