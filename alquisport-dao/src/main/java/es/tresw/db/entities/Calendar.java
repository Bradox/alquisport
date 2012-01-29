package es.tresw.db.entities;

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
@Table(name="CALENDAR")
public class Calendar 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@OneToMany(mappedBy="calendar")	
	private List<Year> years;
	@ManyToOne
	@JoinColumn(name = "ID_COURT")
	private Court court;
	
	public Calendar()
	{
		
	}
	
	public Calendar(List<Year> years, Court court) {
		super();
		this.years = years;
		this.court = court;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public List<Year> getYears() {
		return years;
	}
	public void setYears(List<Year> years) {
		this.years = years;
	}
	public Court getCourt() {
		return court;
	}
	public void setCourt(Court court) {
		this.court = court;
	}
	
	
}
