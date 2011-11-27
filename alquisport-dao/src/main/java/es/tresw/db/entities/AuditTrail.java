package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AUDIT_TRAIL", catalog="Alquisport")
public class AuditTrail
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="DESCRIPTION",columnDefinition="TEXT")
	private String description;
	
	public AuditTrail()
	{
		
	}
			
	public AuditTrail(Long id, String description) 
	{
		this.id = id;
		this.description = description;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}

	
	
}
