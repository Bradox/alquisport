package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_CouponDao;
import es.tresw.db.entities.Coupon;


@Repository("couponDao")
public class CouponDao extends GenericDao<Coupon, Long> implements  I_CouponDao {

}
