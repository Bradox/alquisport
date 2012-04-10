package es.tresw.db.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MESSAGE")
public class Message 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	@Column(name="STATE", nullable=false)
	private int state;
	@Column(name="DATE_SEND", nullable=false)
	private Date dateSend;
	@Column(name="DATE_READ", nullable=false)
	private Date dateRead;
	@Column(name="TEXT", nullable=false, columnDefinition="TEXT")
	private String text;
	@Column(name="SUBJECT", nullable=false)
	private String subject;
	@ManyToOne
	@JoinColumn (name="USER_TO_ID")
	private User userTo;
	@ManyToOne
	@JoinColumn (name="USER_FROM_ID")
	private User userFrom;
	
	public Message()
	{
		
	}

	public Message(User userTo, User userFrom, int state, Date dateSend, Date dateRead, String subject, String text) 
	{
		this.userTo = userTo;
		this.userFrom = userFrom;
		this.state = state;
		this.dateSend = dateSend;
		this.dateRead = dateRead;
		this.text=text;
		this.subject=subject;
	}

	public Long getId() 
	{
		return id;
	}

	public void setId(Long id) 
	{
		this.id = id;
	}

	public User getUserTo() 
	{
		return userTo;
	}

	public void setUserTo(User userTo) 
	{
		this.userTo = userTo;
	}

	public User getUserFrom() 
	{
		return userFrom;
	}

	public void setUserFrom(User userFrom) 
	{
		this.userFrom = userFrom;
	}

	public int getState() 
	{
		return state;
	}

	public void setState(int state) 
	{
		this.state = state;
	}

	public Date getDateSend() 
	{
		return dateSend;
	}

	public void setDateSend(Date dateSend) 
	{
		this.dateSend = dateSend;
	}

	public Date getDateRead() 
	{
		return dateRead;
	}

	public void setDateRead(Date dateRead) 
	{
		this.dateRead = dateRead;
	}

	public String getText() 
	{
		return text;
	}

	public void setText(String text) 
	{
		this.text = text;
	}

	public String getSubject() 
	{
		return subject;
	}

	public void setSubject(String subject) 
	{
		this.subject = subject;
	}

}
