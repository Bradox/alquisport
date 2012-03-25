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
import es.tresw.db.entities.Month;
import es.tresw.db.entities.Year;
import es.tresw.db.types.MonthName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestMonthDao 
{

	@Autowired
	private I_MonthDao monthDao;
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
		assertNotNull(monthDao.read(month.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		Month month = monthDao.readAll().get(0);
		month.setMonthName(MonthName.APRIL);
		monthDao.create(month);
		assertEquals(MonthName.APRIL, month.getMonthName());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(monthDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = monthDao.readAll().get(0).getId();
		assertNotNull(monthDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Month month = monthDao.readAll().get(0);
		monthDao.delete(month);
		assertNotNull(monthDao.read(month.getId()));
	}

	public void setMonthDao(I_MonthDao monthDao) 
	{
		this.monthDao = monthDao;
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

