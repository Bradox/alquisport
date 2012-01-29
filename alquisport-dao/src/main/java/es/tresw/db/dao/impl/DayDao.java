package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_DayDao;
import es.tresw.db.entities.Day;

@Repository("dayDao")
public class DayDao extends GenericDao<Day, Long> implements  I_DayDao {

}
