package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_SpecialDayDao;
import es.tresw.db.entities.SpecialDay;

@Repository("specialDayDao")
public class SpecialDayDao extends GenericDao<SpecialDay, Long> implements  I_SpecialDayDao {

}
