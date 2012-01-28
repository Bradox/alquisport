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

import es.tresw.db.entities.Coupon;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestCouponDao extends TestCase{
	

	@Autowired
	private I_CouponDao couponDao;
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Coupon coupon = new Coupon();
		coupon.setCode("12");
		coupon.setDiscountQuantity(10);
		coupon.setDiscountType(1);
		coupon.setMaxTimesUse(10);
		coupon.setTimesUsed(0);
		couponDao.create(coupon);
		assertNotNull(couponDao.read(coupon.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		List<Coupon> coupons = couponDao.readAll();
		Coupon couponToUpdate = coupons.get(coupons.size()-1);
		couponToUpdate.setDiscountQuantity(12);
		couponDao.update(couponToUpdate);
		assertEquals(0, Float.compare(new Float(12.0), couponDao.read(couponToUpdate.getId()).getDiscountQuantity()));
	}
	
	@Test
	public void testReadAll()
	{
		assertNotNull(couponDao.readAll());
	}
	
	@Test
	public void testReadOne()
	{
		List<Coupon> coupons = couponDao.readAll();
		Coupon couponToRead = coupons.get(coupons.size()-1);
		assertNotNull(couponDao.read(couponToRead.getId()));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		List<Coupon> coupons = couponDao.readAll();
		Coupon couponToDelete = coupons.get(coupons.size()-1);
		couponDao.delete(couponToDelete);
		assertNull(couponDao.read(couponToDelete.getId()));
	}

	

	public void setCouponDao(I_CouponDao couponDao)
	{
		this.couponDao=couponDao;
	}
}
