package es.tresw.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_MunicipalityDao;
import es.tresw.db.dao.I_ProvinceDao;
import es.tresw.db.dao.I_SportFacilityDao;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;

@Transactional
public class SportFacilityService {
	
	@Autowired
	private I_SportFacilityDao dao;
	
	@Autowired
	private I_ProvinceDao provinceDao;
	
	@Autowired
	private I_MunicipalityDao municipalityDao;
	
	public void createSportFacility(SportFacility sf)
	{
		dao.create(sf);
	}
	
	public void updateSportFacility(SportFacility sf)
	{
		dao.update(sf);
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

	public I_SportFacilityDao getDao() {
		return dao;
	}

	public void setDao(I_SportFacilityDao dao) {
		this.dao = dao;
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
