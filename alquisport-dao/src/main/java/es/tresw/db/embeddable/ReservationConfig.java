package es.tresw.db.embeddable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReservationConfig 
{

	@Column(name="DAYS_CLIENT", length=2)
	private int daysClient;
	@Column(name="DAYS_MEMBER", length=2)
	private int daysMember;
	@Column(name="RESERVATION_TYPE", length=2)
	private int reservationType;
	@Column(name="HOUR_LIGHTS_ON", length=2)
	private int hourLightsOn;
	
	public ReservationConfig()
	{
		
	}

	public ReservationConfig(int daysClient, int daysMember,int reservationType, int hourLightsOn) 
	{
		this.daysClient = daysClient;
		this.daysMember = daysMember;
		this.reservationType = reservationType;
		this.hourLightsOn = hourLightsOn;
	}

	public int getDaysClient() 
	{
		return daysClient;
	}

	public void setDaysClient(int daysClient) 
	{
		this.daysClient = daysClient;
	}

	public int getDaysMember() 
	{
		return daysMember;
	}

	public void setDaysMember(int daysMember) 
	{
		this.daysMember = daysMember;
	}

	public int getReservationType() 
	{
		return reservationType;
	}

	public void setReservationType(int reservationType) 
	{
		this.reservationType = reservationType;
	}

	public int getHourLightsOn() 
	{
		return hourLightsOn;
	}

	public void setHourLightsOn(int hourLightsOn) 
	{
		this.hourLightsOn = hourLightsOn;
	}
	
}
