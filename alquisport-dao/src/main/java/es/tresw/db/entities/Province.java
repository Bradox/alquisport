package es.tresw.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="province"/*, catalog="PISTEA"*/)
public class Province 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME")
	private String name;
	@OneToMany(mappedBy="province")
	private Set<Municipality> municipalities=new HashSet<Municipality>();
	
	public Province()
	{
		
	}
	
	public Province(String name, Set<Municipality> municipalities)
	{
		this.name = name;
		this.municipalities = municipalities;
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
	
	public Set<Municipality> getMunicipalities() 
	{
		return municipalities;
	}
	
	public void setMunicipalities(Set<Municipality> municipalities) 
	{
		this.municipalities = municipalities;
	}
	
}
