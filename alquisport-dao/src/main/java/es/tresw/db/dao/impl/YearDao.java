package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_YearDao;
import es.tresw.db.entities.Year;

@Repository("yearDao")
public class YearDao extends GenericDao<Year, Long> implements  I_YearDao {

}
