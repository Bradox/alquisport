package es.tresw.db.dao;

import java.util.List;

import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.AdministratorSportFacility;

public interface I_AdministratorSportFacilityDao extends I_GenericDao<AdministratorSportFacility, Long> 
{

	public List<AdministratorSportFacility> getSportFacilityByAdministratro(Administrator a);
	
}
