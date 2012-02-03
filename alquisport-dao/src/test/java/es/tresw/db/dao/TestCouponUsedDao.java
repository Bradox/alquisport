package es.tresw.db.dao;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Client;
import es.tresw.db.entities.Coupon;
import es.tresw.db.entities.CouponsUsed;
import es.tresw.db.entities.Rental;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestCouponUsedDao extends TestCase{
	

	@Autowired
	private I_CouponUsedDao couponUsedDao;
	@Autowired
	private I_ClientDao clientDao;
	@Autowired
	private I_RentDao rentDao;
	@Autowired
	private I_CouponDao couponDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Client client = clientDao.readAll().get(0);
		Coupon coupon = couponDao.readAll().get(0);
		Rental rental = rentDao.readAll().get(0);
		CouponsUsed couponUsed = new CouponsUsed();
		couponUsed.setClient(client);
		couponUsed.setCoupon(coupon);
		couponUsed.setRent(rental);
		couponUsedDao.create(couponUsed);
		assertNotNull(couponUsedDao.read(couponUsed.getId()));
	}
	
	@Test
	@Rollback(false)
	public void testUpdate()
	{
		List<CouponsUsed> couponsUsed = couponUsedDao.readAll();
		CouponsUsed couponsUsedToUpdate = couponsUsed.get(0);
		Client client = clientDao.readAll().get(1);
		couponsUsedToUpdate.setClient(client);
		couponUsedDao.update(couponsUsedToUpdate);
		assertEquals(client, couponUsedDao.read(couponsUsedToUpdate.getId()).getClient());
	}
	
	@Test
	public void testReadAll()
	{
		assertNotNull(couponUsedDao.readAll());
	}
	
	@Test
	public void testReadOne()
	{
		List<CouponsUsed> couponsUsed = couponUsedDao.readAll();
		CouponsUsed couponUsedToRead = couponsUsed.get(couponsUsed.size()-1);
		assertNotNull(couponUsedDao.read(couponUsedToRead.getId()));
	}
	
	@Test
	public void testDelete()
	{
		List<Coupon> coupons = couponDao.readAll();
		Coupon couponToDelete = coupons.get(coupons.size()-1);
		couponDao.delete(couponToDelete);
		assertNotNull(couponDao.read(couponToDelete.getId()));
	}

	

	public void setCouponUsedDao(I_CouponUsedDao couponUsedDao)
	{
		this.couponUsedDao=couponUsedDao;
	}
	
	public void setClientDao(I_ClientDao clientDao)
	{
		this.clientDao=clientDao;
	}
	
	public void setRentDao(I_RentDao rentDao)
	{
		this.rentDao=rentDao;
	}
	
	public void setCouponDao(I_CouponDao couponDao)
	{
		this.couponDao=couponDao;
	}
}
