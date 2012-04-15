package es.tresw.db.dao;

import java.util.List;

import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.SportFacility;

public interface I_SportFacilityDao extends I_GenericDao<SportFacility, Long> 
{
	public List<SportFacility> getSportFacilityByAdministrator(Administrator a);

}
