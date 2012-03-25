package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.Zone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestMunicipalityDao {

	@Autowired
	private I_MunicipalityDao municipalityDao;
	@Autowired
	private I_ProvinceDao provinceDao;
	@Autowired
	private I_ZoneDao zoneDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Province province = new Province();
		province.setName("TEST");
		provinceDao.create(province);
		Municipality municipality = new Municipality();
		municipality.setName("TEST");
		municipalityDao.create(municipality);
		Zone zone = new Zone();
		zone.setMunicipality(municipality);
		zone.setName("test");
		zoneDao.create(zone);
		Municipality muniAux = municipalityDao.read(municipality.getId());
		assertNotNull(muniAux);
		assertNotNull(muniAux.getZones());
	}
	
	@Test
	public void testUpdate()
	{
		Municipality municipality = municipalityDao.readAll().get(0);
		String name = "Test"+new Date().getTime();
		municipality.setName(name);
		Municipality muniAux = municipalityDao.read(municipality.getId());
		assertEquals(name, muniAux.getName());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(municipalityDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = municipalityDao.readAll().get(0).getId();
		assertNotNull(municipalityDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Municipality municipality = municipalityDao.readAll().get(0);
		municipalityDao.delete(municipality);
		assertNull(municipalityDao.read(municipality.getId()));
	}

	@Test
	public void getMunicipalityByProvince()
	{
		Province province=provinceDao.readAll().get(0);
		List<Municipality> municipalities1 = municipalityDao.getMunicipalityByProvince(province.getId());
		List<Municipality> municipalities2 = municipalityDao.getMunicipalityByProvince(province);
		assertTrue(municipalities1.size()==municipalities2.size());
	}

	public void setMunicipalityDao(I_MunicipalityDao municipalityDao) 
	{
		this.municipalityDao = municipalityDao;
	}

	public void setProvinceDao(I_ProvinceDao provinceDao) 
	{
		this.provinceDao = provinceDao;
	}

	public void setZoneDao(I_ZoneDao zoneDao) 
	{
		this.zoneDao = zoneDao;
	}
	
	

}

