package es.tresw.db.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="MUNICIPALITY", catalog="Alquisport")
public class Municipality 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME")
	private String name;
	@OneToMany(mappedBy="municipality")
	private List<Province> provinces;
	
	public Municipality()
	{
		
	}
	
	public Municipality(Long id, String name, List<Province> provinces) 
	{
		this.id = id;
		this.name = name;
		this.provinces = provinces;
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
	
	public List<Province> getProvinces() 
	{
		return provinces;
	}
	public void setProvinces(List<Province> provinces) {
		this.provinces = provinces;
	}
	
	
	
}
