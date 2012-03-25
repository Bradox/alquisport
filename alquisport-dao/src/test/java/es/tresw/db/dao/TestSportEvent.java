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

import es.tresw.db.entities.Calendar;
import es.tresw.db.entities.Court;
import es.tresw.db.entities.Day;
import es.tresw.db.entities.Month;
import es.tresw.db.entities.Schedule;
import es.tresw.db.entities.Year;
import es.tresw.db.types.DayOfWeek;
import es.tresw.db.types.MonthName;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestSportEvent {

	@Autowired
	private I_CalendarDao calendarDao;
	@Autowired
	private I_YearDao yearDao;
	@Autowired
	private I_CourtDao courtDao;
	@Autowired
	private I_MonthDao monthDao;
	@Autowired
	private I_DayDao dayDao;
	@Autowired
	private I_ScheduleDao scheduleDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Calendar cal=null;
		Court court = courtDao.readAll().get(0);
		Schedule schedule = new Schedule();
		schedule.setEndHour(21);
		schedule.setStartHour(9);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		schedule.setPriceCourt(10F);
		schedule.setPriceLight(5F);
		scheduleDao.create(schedule);
		Day day = new Day();
		day.setSchedule(schedule);
		day.setDay(30);
		day.setDayOfWeek(DayOfWeek.FRIDAY);
		dayDao.create(day);
		Set<Day> days = new HashSet<Day>();
		days.add(day);
		Month month = new Month();
		month.setDays(days);
		month.setMonthName(MonthName.APRIL);
		monthDao.create(month);
		Set<Month> months = new HashSet<Month>();
		months.add(month);
		Year year = new Year();
		year.setMonths(months);
		Set<Year> years;		
		if(court.getCalendar()==null)
		{
			cal = new Calendar();
			years = new HashSet<Year>();
			years.add(year);
			yearDao.create(year);
		}
		else
		{
			if(court.getCalendar().getYears()==null)
			{
				years = new HashSet<Year>();
				years.add(year);
			}
			else
			{
				years = court.getCalendar().getYears();
				years.add(year);
			}
		}	
		cal.setYears(years);
		cal.setCourt(court);
		calendarDao.create(cal);
		Calendar calAux = calendarDao.read(cal.getId());
		assertNotNull(calAux);
	}
	
	@Test
	public void testUpdate()
	{
		Calendar calendar = calendarDao.readAll().get(0);
		Schedule schedule = new Schedule();
		schedule.setEndHour(21);
		schedule.setStartHour(9);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		schedule.setPriceCourt(10F);
		schedule.setPriceLight(5F);
		scheduleDao.create(schedule);
		Day day = new Day();
		day.setSchedule(schedule);
		day.setDay(30);
		day.setDayOfWeek(DayOfWeek.FRIDAY);
		dayDao.create(day);
		Year year = calendar.getYears().iterator().next();
		year.getMonths().iterator().next().getDays().add(day);
		yearDao.create(year);
		Year yearAux = yearDao.read(year.getId());
		Iterator<Day> daysIterator = yearAux.getMonths().iterator().next().getDays().iterator();
		boolean enc = false;
		while(daysIterator.hasNext() && !enc)
		{
			Day dayAux = daysIterator.next();
			if(dayAux.getDay()==day.getDay() && dayAux.getMonth().getMonthName().equals(day.getMonth().getMonthName()))
			{
				enc=true;
			}
		}
		assertTrue(enc);
	}
	
	@Test
	public void testReadAll()
	{
		List<Calendar> calendars = calendarDao.readAll();
		assertTrue(calendars.size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = calendarDao.readAll().get(0).getId();
		Calendar cal = calendarDao.read(id);
		assertNotNull(cal);
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Long id = calendarDao.readAll().get(0).getId();
		calendarDao.delete(calendarDao.read(id));
		assertNull(calendarDao.read(id));
	}

	public void setCalendarDao(I_CalendarDao calendarDao)
	{
		this.calendarDao=calendarDao;
	}

	public void setYearDao(I_YearDao yearDao)
	{
		this.yearDao=yearDao;
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
