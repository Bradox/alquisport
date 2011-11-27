package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_DayClosedDao;
import es.tresw.db.entities.DayClosed;

@Repository("dayClosedDao")
public class DayClosedDao extends GenericDao<DayClosed, Long> implements  I_DayClosedDao {

}
