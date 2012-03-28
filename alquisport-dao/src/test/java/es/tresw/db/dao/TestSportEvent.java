package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import es.tresw.db.entities.Court;
import es.tresw.db.entities.Day;
import es.tresw.db.entities.Schedule;
import es.tresw.db.types.DayOfWeek;
import es.tresw.db.types.MonthName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestSportEvent {


	@Autowired
	private I_CourtDao courtDao;
	@Autowired
	private I_DayDao dayDao;
	@Autowired
	private I_ScheduleDao scheduleDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Court court = courtDao.readAll().get(0);
		Schedule schedule = new Schedule();
		schedule.setEndHour(21);
		schedule.setStartHour(9);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		scheduleDao.create(schedule);
		Day day = new Day();
		day.setSchedule(schedule);
		day.setDay(30);
		day.setDayOfWeek(DayOfWeek.FRIDAY);
		dayDao.create(day);
		Set<Day> days = new HashSet<Day>();
		days.add(day);
	}
	
	@Test
	public void testUpdate()
	{
	
	}
	
	@Test
	public void testReadAll()
	{
	}
	
	@Test
	public void testReadOne()
	{
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
	}

		
	public void setCourtDao(I_CourtDao courtDao)
	{
		this.courtDao=courtDao;
	}
	
	public void setScheduleDao(I_ScheduleDao scheduleDao)
	{
		this.scheduleDao=scheduleDao;
	}
}
