package es.tresw.db.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ZoneDao;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Zone;


@Repository("zoneDao")
public class ZoneDao extends GenericDao<Zone, Long> implements  I_ZoneDao {

	@Override
	public Zone getZoneByName(String name) {
		Criteria criteria = getSession().createCriteria(Zone.class);
		criteria.add(Restrictions.eq("name", name));
		
		return (Zone)criteria.uniqueResult();
	}

	@Override
	public Zone getZoneByNameAndMunicipality(String name, Municipality mun) {
		Criteria criteria = getSession().createCriteria(Zone.class);
		criteria.add(Restrictions.eq("name", name));
		criteria.add(Restrictions.eq("municipality", mun));
		
		return (Zone)criteria.uniqueResult();
	}

}
