package es.tresw.db.dao;

import static org.junit.Assert.*;

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
import es.tresw.db.types.MonthName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestDayDao {

	@Autowired
	private I_DayDao dayDao;
	@Autowired
	private I_ScheduleDao scheduleDao;
	@Autowired
	private I_CourtDao courtDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Court court = courtDao.readAll().get(0);
		Schedule schedule = new Schedule();
		schedule.setEndHour(22);
		schedule.setStartHour(9);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		scheduleDao.create(schedule);
		Day day = new Day();
		day.setSchedule(schedule);
		day.setDay(1);
		dayDao.create(day);
		assertNotNull(dayDao.read(day.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		int dayInt = 24;
		Day day = dayDao.readAll().get(0);
		day.setDay(dayInt);
		dayDao.create(day);
		Day dayAux = dayDao.read(day.getId());
		assertEquals(dayInt, dayAux.getDay());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(dayDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = dayDao.readAll().get(0).getId();
		assertNotNull(dayDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Day day = dayDao.readAll().get(0);
		dayDao.delete(day);
		assertNotNull(dayDao.read(day.getId()));

	}

	public void setDayDao(I_DayDao dayDao) 
	{
		this.dayDao = dayDao;
	}

	public void setScheduleDao(I_ScheduleDao scheduleDao) 
	{
		this.scheduleDao = scheduleDao;
	}

	public void setCourtDao(I_CourtDao courtDao) 
	{
		this.courtDao = courtDao;
	}

}
