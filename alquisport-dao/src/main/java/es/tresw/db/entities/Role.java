package es.tresw.db.entities;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "ROLE", catalog = "Alquisport")
public class Role
{


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(mappedBy = "roles")
	private List<User> users=new ArrayList<User>();

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


	public List<User> getUserCollection() 
	{
		return users;
	}

	
	public void setUserCollection(List<User> users) 
	{
		this.users = users;
	}


}