package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.constants.PisteaConstants;
import es.tresw.db.entities.Court;
import es.tresw.db.entities.Rental;
import es.tresw.db.entities.SportsEvent;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestSportsEventDao 
{
	@Autowired
	private I_SportsEventDao sportsEventDao;
	@Autowired
	private I_RentDao rentDao;
	@Autowired
	private I_CourtDao courtDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		SportsEvent sportsEvent = new SportsEvent();
		sportsEvent.setDescription("El evento mas guapo del año");
		sportsEvent.setName("Waka Waka");
		Court court = courtDao.readAll().get(0);
		Rental rental = new Rental();
		rental.setCourt(court);
		rental.setDateEnd(new Date());
		rental.setDateStart(new Date());
		rental.setPaymentState(PisteaConstants.PAYMENT_STATE_NO_PAID);
		rental.setQuantityPaid(0);
		rentDao.create(rental);
		Set<Rental> rentals = new HashSet<Rental>();
		rentals.add(rental);
		sportsEvent.setRents(rentals);
		sportsEvent.setSportFacility(court.getSportFacility());
		sportsEventDao.create(sportsEvent);
		assertNotNull(sportsEventDao.read(sportsEvent.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		SportsEvent sportsEvent = sportsEventDao.readAll().get(0);
		sportsEvent.setDescription("Waka Waka Shakira Style");
		sportsEventDao.create(sportsEvent);
		SportsEvent sp = sportsEventDao.read(sportsEvent.getId());
		assertEquals("Waka Waka Shakira Style", sp.getDescription());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(sportsEventDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		SportsEvent sportsEvent = sportsEventDao.readAll().get(0);
		assertNotNull(sportsEventDao.read(sportsEvent.getId()));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		SportsEvent sportsEvent = sportsEventDao.readAll().get(0);
		sportsEventDao.delete(sportsEvent);
		assertNotNull(sportsEventDao.read(sportsEvent.getId()));
	}

	public void setSportsEventDao(I_SportsEventDao sportsEventDao) 
	{
		this.sportsEventDao = sportsEventDao;
	}

	public void setRentDao(I_RentDao rentDao) 
	{
		this.rentDao = rentDao;
	}

	public void setCourtDao(I_CourtDao courtDao)
	{
		this.courtDao = courtDao;
	}
	
}
