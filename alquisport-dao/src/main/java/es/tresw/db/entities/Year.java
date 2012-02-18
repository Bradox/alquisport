package es.tresw.db.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="YEAR",catalog="Alquisport")
public class Year 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="YEAR")
	private int year;
	@ManyToOne
	@JoinColumn(name = "ID_CALENDAR")
	private Calendar calendar=new Calendar();	
	@OneToMany(mappedBy="year")	
	private List<Month> months=new ArrayList<Month>();
	
	public Year()
	{
		
	}
	
	public Year(int year, Calendar calendar, List<Month> months)
	{
		this.year = year;
		this.calendar = calendar;
		this.months = months;
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
	
	public Calendar getCalendar()
	{
		return calendar;
	}
	
	public void setCalendar(Calendar calendar) 
	{
		this.calendar = calendar;
	}
	
	public List<Month> getMonths() 
	{
		return months;
	}
	
	public void setMonths(List<Month> months)
	{
		this.months = months;
	}	
	
	

}
