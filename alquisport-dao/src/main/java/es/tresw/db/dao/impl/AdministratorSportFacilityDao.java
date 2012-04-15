package es.tresw.db.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_AdministratorSportFacilityDao;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.AdministratorSportFacility;

@Repository("administratorSportFacilityDao")
public class AdministratorSportFacilityDao extends GenericDao<AdministratorSportFacility, Long> implements  I_AdministratorSportFacilityDao 
{

	@Override
	public List<AdministratorSportFacility> getSportFacilityByAdministratro(Administrator a) {
		Criteria criteria = getSession().createCriteria(AdministratorSportFacility.class);
		criteria.add(Restrictions.eq("administrator", a));
		return criteria.list();
	}

}