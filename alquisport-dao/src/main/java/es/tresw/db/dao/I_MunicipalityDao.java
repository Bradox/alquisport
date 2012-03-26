package es.tresw.db.dao;

import java.util.List;

import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;

public interface I_MunicipalityDao extends I_GenericDao<Municipality, Long> 
{
	public List<Municipality> getMunicipalityByProvince(Province p);
	public List<Municipality> getMunicipalityByProvince(Long idProvince);
}
