package es.tresw.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE", catalog = "PISTEA")
public class Role
{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	private UserRole users;

	public Role() 
	{

	}

	public Role(String name, Long id) 
	{
		this.id = id;
		this.name = name;
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


	public UserRole getUserCollection() 
	{
		return users;
	}

	
	public void setUserCollection(UserRole users) 
	{
		this.users = users;
	}


}