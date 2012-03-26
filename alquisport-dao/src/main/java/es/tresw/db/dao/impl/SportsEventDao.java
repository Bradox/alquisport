package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_SportsEventDao;
import es.tresw.db.entities.SportsEvent;


@Repository("sportsEventDao")
public class SportsEventDao extends GenericDao<SportsEvent, Long> implements  I_SportsEventDao {

}
