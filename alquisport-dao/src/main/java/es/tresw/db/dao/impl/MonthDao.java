package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_MonthDao;
import es.tresw.db.entities.Month;

@Repository("monthDao")
public class MonthDao extends GenericDao<Month, Long> implements  I_MonthDao {

}
