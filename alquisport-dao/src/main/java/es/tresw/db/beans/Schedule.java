package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private DayOfWeek dayOfWeek;
	private int startHour;
	private int endHour;
	private int minStart;
	private int minEnd;
	private float price;
	
}
