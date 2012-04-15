package es.tresw.db.dao;

import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Zone;

public interface I_ZoneDao extends I_GenericDao<Zone, Long> 
{
	public Zone getZoneByName(String name);
	
	public Zone getZoneByNameAndMunicipality(String name, Municipality mun);
}
