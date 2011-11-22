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
	private List<GroupAuthority> groupAuthorities;
	@OneToMany(mappedBy="group")
	private List<GroupMember> members;
	
	public Group()
	{
	
	}
	
	public Group(Long id, String name, List<GroupAuthority>groupAuthorities, List<GroupMember> members)
	{
		this.id = id;
		this.name = name;
		this.groupAuthorities=groupAuthorities;
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

	public void setGroupAuthorities(List<GroupAuthority>authorities)
	{
		this.groupAuthorities=authorities;
	}
	
	public List<GroupAuthority> getGroupAuthorities()
	{
		return this.groupAuthorities;
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
