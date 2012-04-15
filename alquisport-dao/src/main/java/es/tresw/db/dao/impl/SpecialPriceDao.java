package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_SpecialPriceDao;
import es.tresw.db.entities.SpecialPrice;

@Repository("specialPriceDao")
public class SpecialPriceDao extends GenericDao<SpecialPrice, Long> implements  I_SpecialPriceDao {

}
