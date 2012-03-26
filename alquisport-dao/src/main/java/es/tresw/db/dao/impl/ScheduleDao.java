package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ScheduleDao;
import es.tresw.db.entities.Schedule;

@Repository("scheduleDao")
public class ScheduleDao extends GenericDao<Schedule, Long> implements  I_ScheduleDao {

}
