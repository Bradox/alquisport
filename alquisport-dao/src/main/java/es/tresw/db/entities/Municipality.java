package es.tresw.db.entities;


import java.util.Set;
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
@Table(name="MUNICIPALITY")
public class Municipality 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME")
	private String name;
	@ManyToOne
	@JoinColumn(name = "ID_PROVINCE")
	private Province province;
	@OneToMany(mappedBy="municipality")
	private Set<Zone> zones;
	
	public Municipality()
	{
		
	}
	
	public Municipality(String name, Province province, Set<Zone> zones) 
	{
		this.name = name;
		this.province = province;
		this.zones=zones;
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
	
	public Province getProvinces() 
	{
		return province;
	}
	
	public void setProvinces(Province province) 
	{
		this.province = province;
	}
	
	public void setZones(Set<Zone> zones)
	{
		this.zones=zones;
	}
	
	public Set<Zone> getZones()
	{
		return this.zones;
	}
	
}
