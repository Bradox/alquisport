package es.tresw.db.dao;


import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Schedule;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestScheduleDao 
{

	@Autowired
	private I_ScheduleDao scheduleDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Schedule schedule = new Schedule();
		schedule.setEndHour(21);
		schedule.setMinEnd(00);
		schedule.setMinStart(00);
		schedule.setStartHour(9);
		scheduleDao.create(schedule);
		assertNotNull(scheduleDao.read(schedule.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		Schedule schedule = scheduleDao.readAll().get(0);
		scheduleDao.create(schedule);
		Schedule scheduleAux = scheduleDao.read(schedule.getId());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(scheduleDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id =scheduleDao.readAll().get(0).getId();
		assertNotNull(scheduleDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Schedule schedule = scheduleDao.readAll().get(0);
		scheduleDao.delete(schedule);
		assertNull(scheduleDao.read(schedule.getId()));
	}

	public void setScheduleDao(I_ScheduleDao scheduleDao) 
	{
		this.scheduleDao = scheduleDao;
	}

}
