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
@Table(name = "GROUP_AUTHORITIES", catalog="Alquisport")
public class GroupAuthority 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "ID_GROUP")
	private Group group;
	@ManyToOne
	@JoinColumn(name = "ID_AUTHORITY")
	private Authority authority;
	
	public GroupAuthority()
	{
		
	}
	
	public GroupAuthority(Long id, Group group, Authority authority) 
	{
		this.id = id;
		this.group = group;
		this.authority = authority;
	}

	public Long getId() 
	{
		return id;
	}
	
	public void setId(Long id) 
	{
		this.id = id;
	}
	
	public Group getGroup() 
	{
		return group;
	}
	
	public void setGroup(Group group) 
	{
		this.group = group;
	}
	
	public Authority getAuthority() 
	{
		return authority;
	}
	
	public void setAuthority(Authority authority) 
	{
		this.authority = authority;
	}

	
}
