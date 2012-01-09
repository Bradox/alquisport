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
@Table(name = "GROUP_MEMBERS", catalog="ALQUISPORT")
public class GroupMember  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@ManyToOne
    @JoinColumn(name="GROUP_ID")
    private Group group;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;


    public GroupMember()
	{
    }

	
	public GroupMember(Long id, Group group, User user) 
	{
		this.id = id;
		this.group = group;
		this.user = user;
	}

	
	public Long getId() 
	{
		return id;
	}

	public void setId(Long authorityId) 
	{
		this.id = authorityId;
	}

	public Group getGroup() {
		return this.group;
	}

	public void setGroup(Group group) 
	{
		this.group = group;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
 
}
