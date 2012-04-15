package es.tresw.db.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_SportFacilityDao;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.SportFacility;

@Repository("sportFacilityDao")
public class SportFacilityDao extends GenericDao<SportFacility, Long> implements  I_SportFacilityDao {

	@Override
	public List<SportFacility> getSportFacilityByAdministrator(Administrator a) {
		Criteria criteria = getSession().createCriteria(SportFacility.class);
		criteria.createAlias("administratorSportFacilities", "a");
		criteria.add(Restrictions.eq("a.administrator", a));
		return criteria.list();
	}

}
