package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE", catalog="Alquisport")
public class Image 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="NAME", nullable=false)
	private String name;
	@Column(name="DESCRIPTION", columnDefinition="TEXT")
	private String description;
	@Lob
	private byte[] image;
			
	public Image(String name, String description, byte[] image) 
	{
		super();
		this.name = name;
		this.description = description;
		this.image = image;
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
	
	public String getDescription() 
	{
		return description;
	}
	
	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	public byte[] getImage() 
	{
		return image;
	}
	
	public void setImage(byte[] image) 
	{
		this.image = image;
	}
	
}
