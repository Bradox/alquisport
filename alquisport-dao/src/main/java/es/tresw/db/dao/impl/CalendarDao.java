package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_CalendarDao;
import es.tresw.db.entities.Calendar;

@Repository("calendarDao")
public class CalendarDao extends GenericDao<Calendar, Long> implements  I_CalendarDao {

}
