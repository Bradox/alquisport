package es.tresw.db.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_MunicipalityDao;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;

@Repository("municipalityDao")
public class MunicipalityDao extends GenericDao<Municipality, Long> implements  I_MunicipalityDao {

	@Override
	@SuppressWarnings("unchecked")
	public List<Municipality> getMunicipalityByProvince(Province p) {
		
		Criteria criteria = getSession().createCriteria(Municipality.class);
		
		criteria.add(Restrictions.eq("province", p));
		
		criteria.addOrder(Order.asc("name"));
		
		return (List<Municipality>) criteria.list();
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Municipality> getMunicipalityByProvince(Long idProvince) {
		
		Criteria criteria = getSession().createCriteria(Municipality.class);
		criteria.createAlias("province", "p");
		criteria.add(Restrictions.eq("p.id", idProvince));
		
		criteria.addOrder(Order.asc("name"));
		
		return (List<Municipality>)criteria.list();
	}

}
