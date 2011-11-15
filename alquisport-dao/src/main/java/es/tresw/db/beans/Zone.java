package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="PROVINCE", catalog="Alquisport")
public class Zone 
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

	public Zone()
	{
		
	}
	
	public Zone(Long id, String name, Province province) 
	{
		this.id = id;
		this.name = name;
		this.province = province;
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
	
	public Province getProvince() 
	{
		return province;
	}
	
	public void setProvince(Province province) 
	{
		this.province = province;
	}
	
}
