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
@Table(name="USER_ROLE", catalog="PISTEA")
public class UserRole
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@OneToMany(mappedBy="roles") 
	private Set<User> users=new HashSet<User>();
	@OneToMany(mappedBy="users") 
	private Set<Role> roles=new HashSet<Role>();
	
	public UserRole()
	{
		
	}

	public UserRole(Long id, Set<User> users, Set<Role> roles) 
	{
		this.id = id;
		this.users = users;
		this.roles = roles;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Set<User> getUsers() 
	{
		return users;
	}

	public void setUser(Set<User> users) 
	{
		this.users = users;
	}

	public Set<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(Set<Role> roles)
	{
		this.roles = roles;
	}
}
