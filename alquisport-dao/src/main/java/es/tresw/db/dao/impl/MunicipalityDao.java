package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_MunicipalityDao;
import es.tresw.db.entities.Municipality;

@Repository("municipalityDao")
public class MunicipalityDao extends GenericDao<Municipality, Long> implements  I_MunicipalityDao {

}
