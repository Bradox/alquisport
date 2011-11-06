package es.tresw.db.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="COURT", catalog="Alquisport")
public class Court 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME", columnDefinition="TEXT")
	private String description;
	@Column(name="STATE", columnDefinition="TEXT")
	private int state;
	/**
	 * TODO : ESTO QUE COÑO ES
	 */
	private CourtType courtType;
	@OneToMany
	@JoinTable(name = "COURT_SCHEDULE", 
	     	   joinColumns = { @JoinColumn(name = "COURT_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "SCHEDULE_ID") })
	private Schedule schedule;
	@Embedded
	private ReservationConfig reservationConfig;
	@OneToMany
	@JoinTable(name = "SPORT_FACILITY_FEATURE", 
	     	   joinColumns = { @JoinColumn(name = "SPORT_FACILITY_ID") }, 
	 		   inverseJoinColumns = { @JoinColumn(name = "FEATURE_ID") })
	private List<Feature> features;

}
