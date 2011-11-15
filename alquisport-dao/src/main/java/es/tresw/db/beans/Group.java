package es.tresw.db.beans;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "GROUP", catalog="Alquisport")
public class Group 
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name = "GROUP_NAME", nullable = false)
	private String name;
	@OneToMany(mappedBy="group")
	private List<Authority> authorities;
	@OneToMany(mappedBy="group")
	private List<GroupMember> members;
	
	public Group()
	{
	
	}
	
	public Group(Long id, String name, List<Authority>authorities, List<GroupMember> members)
	{
		this.id = id;
		this.name = name;
		this.authorities=authorities;
		this.members=members;
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

	public void setAuthorities(List<Authority>authorities)
	{
		this.authorities=authorities;
	}
	
	public List<Authority> getAuthorities()
	{
		return this.authorities;
	}

	public List<GroupMember> getMembers()
	{
		return members;
	}

	public void setMembers(List<GroupMember> members)
	{
		this.members = members;
	}
	
	
}
