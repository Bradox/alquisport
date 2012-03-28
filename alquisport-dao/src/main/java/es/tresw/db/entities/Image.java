package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "IMAGE",catalog="PISTEA")
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
	@Column(name="HEIGHT")
	private int height;
	@Column(name="WEIGHT")
	private int weight;
	@Column(name="DISC_PATH", nullable=false, length=255)
	private String discPath;
	@Lob
	private byte[] image;
			
	public Image()
	{
		
	}
	
	public Image(String name, String description, int height,int weight, String discPath, byte[] image) 
	{
		this.name = name;
		this.description = description;
		this.height = height;
		this.weight = weight;
		this.discPath = discPath;
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

	public int getHeight() 
	{
		return height;
	}

	public void setHeight(int height) 
	{
		this.height = height;
	}

	public int getWeight() 
	{
		return weight;
	}

	public void setWeight(int weight) 
	{
		this.weight = weight;
	}

	public String getDiscPath() 
	{
		return discPath;
	}

	public void setDiscPath(String discPath) 
	{
		this.discPath = discPath;
	}
	
}
