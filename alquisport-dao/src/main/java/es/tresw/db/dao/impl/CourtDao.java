package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_CourtDao;
import es.tresw.db.entities.Court;

@Repository("courtDao")
public class CourtDao extends GenericDao<Court, Long> implements  I_CourtDao {

}
