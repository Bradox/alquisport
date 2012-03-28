package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.Date;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestRentDao {

	@Autowired
	private I_CourtDao courtDao;
	@Autowired
	private I_RentDao rentDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Rental rental = new Rental();
		Court court = courtDao.readAll().get(0);
		rental.setCourt(court);
		rental.setDateEnd(new Date());
		rental.setDateStart(new Date());
		rental.setPaymentState(PisteaConstants.PAYMENT_STATE_NO_PAID);
		rental.setQuantityPaid(0);
		rentDao.create(rental);
		assertNotNull(rentDao.read(rental.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		Rental rental = rentDao.readAll().get(0);
		rental.setPaymentState(PisteaConstants.PAYMENT_STATE_PARTIALLY_PAID);
		rental.setQuantityPaid(10);
		rentDao.create(rental);
		assertEquals(PisteaConstants.PAYMENT_STATE_PARTIALLY_PAID, rental.getPaymentState());		
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(rentDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = rentDao.readAll().get(0).getId();
		assertNotNull(rentDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Rental rental = rentDao.readAll().get(0);
		rentDao.delete(rental);
		assertNull(rentDao.read(rental.getId()));
	}

	public void setCourtDao(I_CourtDao courtDao) 
	{
		this.courtDao = courtDao;
	}

	public void setRentDao(I_RentDao rentDao) 
	{
		this.rentDao = rentDao;
	}

	

}
