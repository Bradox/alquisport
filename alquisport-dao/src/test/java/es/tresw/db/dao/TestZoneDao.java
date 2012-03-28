package es.tresw.db.dao;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Zone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestZoneDao 
{
	@Autowired
	private I_ZoneDao zoneDao;
	@Autowired
	private I_MunicipalityDao municipalityDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Municipality municipality = municipalityDao.readAll().get(0);
		Zone zone = new Zone();
		zone.setMunicipality(municipality);
		zone.setName("Bami");
		zoneDao.create(zone);
		assertNotNull(zoneDao.read(zone.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		Zone zone = zoneDao.readAll().get(0);
		String zoneName ="Sergio poyardon";
		zoneDao.create(zone);
		assertEquals(zoneName, zone.getName());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(zoneDao.readAll().size()>0);
	}
	
	
	@Test
	public void testReadOne()
	{
		Long id = zoneDao.readAll().get(0).getId();
		assertNotNull(zoneDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Zone zone = zoneDao.readAll().get(0);
		zoneDao.delete(zone);
		assertNotNull(zoneDao.read(zone.getId()));
	}

	public void setZoneDao(I_ZoneDao zoneDao) {
		this.zoneDao = zoneDao;
	}

	public void setMunicipalityDao(I_MunicipalityDao municipalityDao) {
		this.municipalityDao = municipalityDao;
	}

}



