package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_CouponUsedDao;
import es.tresw.db.entities.CouponsUsed;


@Repository("couponUsedDao")
public class CouponUsedDao extends GenericDao<CouponsUsed, Long> implements  I_CouponUsedDao {

}
