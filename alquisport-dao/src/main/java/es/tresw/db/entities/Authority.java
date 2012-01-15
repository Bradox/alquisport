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
@Table(name = "AUTHORITY", catalog="ALQUISPORT")
public class Authority  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
    @Column(name = "AUTHORITY_NAME", nullable = false)
    private String name;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;


    public Authority()
	{
    }

	
	public Authority(Long id, String name, User user) 
	{
		this.id = id;
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
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
