package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_SportFacilityDao;
import es.tresw.db.entities.SportFacility;

@Repository("sportFacilityDao")
public class SportFacilityDao extends GenericDao<SportFacility, Long> implements  I_SportFacilityDao {

}
