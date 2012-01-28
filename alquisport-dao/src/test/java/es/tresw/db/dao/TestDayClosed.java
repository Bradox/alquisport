package es.tresw.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.DayClosed;
import es.tresw.db.entities.SportFacility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestDayClosed {
	

	@Autowired
	private I_DayClosedDao dayClosedDao;
	@Autowired
	private I_SportFacilityDao sportFacilityDao;

	@Test
	@Rollback(false)
	public void testCreate() 
	{
		List<SportFacility> sportFacilities = sportFacilityDao.readAll();
		SportFacility sportFacility = sportFacilities.get(0);
		DayClosed dayClosed = new DayClosed();
		dayClosed.setDay(1);
		dayClosed.setMonth(12);
		dayClosed.setYear(2010);
		dayClosed.setSportFacility(sportFacility);
		dayClosedDao.create(dayClosed);
		DayClosed dayClosedInserted = dayClosedDao.read(dayClosed.getId());
		assertEquals(12, dayClosedInserted.getMonth());
	}
	
	@Test
	@Rollback(false)
	public void testReadAll()
	{
		assertTrue(dayClosedDao.readAll().size()>0);	
	}
	
	@Test
	@Rollback(false)
	public void testReadOne()
	{
		Long id = dayClosedDao.readAll().get(0).getId();
		assertNotNull(dayClosedDao.read(id));
	}
	
	@Test
	public void testDelete()
	{
		List<DayClosed> daysClosed = dayClosedDao.readAll();
		DayClosed dayDelete = daysClosed.get(daysClosed.size()-1);
		Long idDelete = dayDelete.getId();
		dayClosedDao.delete(dayDelete);
		assertNull(dayClosedDao.read(idDelete));
	}

	
	public void setDayClosedDao(I_DayClosedDao dayClosedDao)
	{
		this.dayClosedDao=dayClosedDao;
	}

	public void setSportFacilityDao(I_SportFacilityDao sportFacilityDao)
	{
		this.sportFacilityDao=sportFacilityDao;
	}
}
