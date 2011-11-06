package es.tresw.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE", catalog="Alquisport")
public class Role 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="role", nullable=false, length=2)
	private Integer role;
	
	public Role()
	{
		
	}

	
	public Role(Integer role) 
	{
		super();
		this.role = role;
	}


	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Integer getRole() 
	{
		return role;
	}

	public void setRole(Integer role) 
	{
		this.role = role;
	}
	
	
}
