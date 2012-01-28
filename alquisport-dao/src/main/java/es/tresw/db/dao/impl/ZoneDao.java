package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ZoneDao;
import es.tresw.db.entities.Zone;


@Repository("zoneDao")
public class ZoneDao extends GenericDao<Zone, Long> implements  I_ZoneDao {

}
