package es.tresw.db.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ZONE")
public class Zone 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME")
	private String name;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "ID_MUNICIPALITY")
	private Municipality municipality;

	public Zone()
	{
		
	}
	
	public Zone(String name, Municipality municipality) 
	{
		this.name = name;
		this.municipality = municipality;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public Municipality getMunicipality() 
	{
		return municipality;
	}

	public void setMunicipality(Municipality municipality) 
	{
		this.municipality = municipality;
	}
	
	
}
