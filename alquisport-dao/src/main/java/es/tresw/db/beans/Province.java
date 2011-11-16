package es.tresw.db.beans;

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
@Table(name="PROVINCE", catalog="Alquisport")
public class Province 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME")
	private String name;
	@ManyToOne
	@JoinColumn(name = "ID_MUNICIPALITY")
	private Municipality municipality;
	@OneToMany(mappedBy="province")
	private List<Zone> zones;
	
	public Province()
	{
		
	}
	
	public Province(Long id, String name, Municipality municipality, List<Zone> zones)
	{
		this.id = id;
		this.name = name;
		this.municipality = municipality;
		this.zones = zones;
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
	
	public List<Zone> getZones() 
	{
		return zones;
	}
	
	public void setZones(List<Zone> zones) 
	{
		this.zones = zones;
	}
	
	

}
