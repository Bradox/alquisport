package es.tresw.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_MunicipalityDao;
import es.tresw.db.dao.I_ProvinceDao;
import es.tresw.db.dao.I_SportFacilityDao;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;

@Transactional
public class PruebaService{
	
	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	
	@Autowired
	private I_ProvinceDao provinceDao;
	
	@Autowired
	private I_MunicipalityDao municipalityDao;
	
	public String prueba()
	{
		return "Desde el service";
	}
	
	public void createSportFacility(SportFacility sf)
	{
		sportFacilityDao.create(sf);
	}
	
	public void updateSportFacility(SportFacility sf)
	{
		sportFacilityDao.update(sf);
	}
	
	public Province getProvince(Long id)
	{
		return provinceDao.read(id);
	}
	
	public Municipality getMunicipality(Long id)
	{
		return municipalityDao.read(id);
	}
	
	public List<Province> getProvinces()
	{
		return provinceDao.readAll();
	}
	
	public List<Municipality> getMunicipality()
	{
		return municipalityDao.readAll();
	}
	
	public List<Municipality> getMunicipalityByProvince(Province p)
	{
		return municipalityDao.getMunicipalityByProvince(p);
	}
	
	public List<Municipality> getMunicipalityByProvince(Long idProvince)
	{
		return municipalityDao.getMunicipalityByProvince(idProvince);
	}
	
	public List<SportFacility> getSportFacilityByAdministrator(Administrator a)
	{
		return sportFacilityDao.getSportFacilityByAdministrator(a);
	}
	
	
	/*GETTERS AND SETTERS*/

	public I_SportFacilityDao getSportFacilityDao() {
		return sportFacilityDao;
	}

	public void setSportFacilityDao(I_SportFacilityDao sportFacilityDao) {
		this.sportFacilityDao = sportFacilityDao;
	}

	public I_ProvinceDao getProvinceDao() {
		return provinceDao;
	}

	public void setProvinceDao(I_ProvinceDao provinceDao) {
		this.provinceDao = provinceDao;
	}

	public I_MunicipalityDao getMunicipalityDao() {
		return municipalityDao;
	}

	public void setMunicipalityDao(I_MunicipalityDao municipalityDao) {
		this.municipalityDao = municipalityDao;
	}
	

}
