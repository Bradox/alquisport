package es.tresw.db.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Calendar;
import es.tresw.db.entities.Court;
import es.tresw.db.entities.Day;
import es.tresw.db.entities.Month;
import es.tresw.db.entities.Schedule;
import es.tresw.db.entities.Year;
import es.tresw.db.types.MonthName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestDayDao {

	@Autowired
	private I_DayDao dayDao;
	@Autowired
	private I_MonthDao monthDao;
	@Autowired
	private I_ScheduleDao scheduleDao;
	@Autowired
	private I_CalendarDao calendarDao;
	@Autowired
	private I_CourtDao courtDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Court court = courtDao.readAll().get(0);
		Calendar calendar = court.getCalendar();
		if(calendar==null)
		{
			calendar = new Calendar();
			calendar.setCourt(court);
			calendarDao.create(calendar);
		}
		Year year = new Year();
		year.setCalendar(calendar);
		Month month = new Month();
		month.setMonthName(MonthName.JANUARY);
		month.setYear(year);
		monthDao.create(month);
		Schedule schedule = new Schedule();
		schedule.setEndHour(22);
		schedule.setStartHour(9);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		schedule.setPriceCourt(10F);
		schedule.setPriceLight(5F);
		scheduleDao.create(schedule);
		Day day = new Day();
		day.setSchedule(schedule);
		day.setDay(1);
		day.setMonth(month);
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

	public void setMonthDao(I_MonthDao monthDao) 
	{
		this.monthDao = monthDao;
	}

	public void setScheduleDao(I_ScheduleDao scheduleDao) 
	{
		this.scheduleDao = scheduleDao;
	}

	public void setCalendarDao(I_CalendarDao calendarDao) 
	{
		this.calendarDao = calendarDao;
	}

	public void setCourtDao(I_CourtDao courtDao) 
	{
		this.courtDao = courtDao;
	}

}
