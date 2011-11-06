package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="FEATURE", catalog="Alquisport")
public class Feature
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="KEY", nullable=false, length=255)
	private String key;
	@Column(name="VALUE", nullable=false, length=255)
	private String value;
	@Column(name="POSITION", nullable=false, length=2)
	private String position;
	
	public Feature()
	{
		
	}
	
	public Feature(Long id, String key, String value, String position) 
	{
		this.id = id;
		this.key = key;
		this.value = value;
		this.position = position;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public String getKey() 
	{
		return key;
	}

	public void setKey(String key) 
	{
		this.key = key;
	}

	public String getValue() 
	{
		return value;
	}

	public void setValue(String value) 
	{
		this.value = value;
	}

	public String getPosition() 
	{
		return position;
	}

	public void setPosition(String position)
	{
		this.position = position;
	}
	
}
