package es.tresw.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Court;
import es.tresw.db.entities.Feature;
import es.tresw.db.entities.SportFacility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestFeatureDao extends AbstractTransactionalJUnit4SpringContextTests {
	

	@Autowired
	private I_FeatureDao featureDao;
	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	@Autowired
	private I_CourtDao courtDao;
	
	@Test
	public void testCreate() 
	{
		Feature feature = new Feature();
		feature.setKey("asdsad");
		feature.setPosition(1);
		feature.setValue("asdsada");
		Set<Feature> features = new HashSet<Feature>();
		features.add(feature);
		List<SportFacility> sportFacilities = sportFacilityDao.readAll();
		SportFacility sportFacility = sportFacilities.get(0);
		features.addAll(sportFacility.getFeatures());
		sportFacility.setFeatures(features);
		sportFacilityDao.update(sportFacility);
		SportFacility sportFacilityUpdated = sportFacilityDao.read(sportFacility.getId());
		assertEquals(features, sportFacilityUpdated.getFeatures());
		Court court =  courtDao.readAll().get(0);
		court.setFeatures(features);
		courtDao.create(court);
		Court courtUpdated = courtDao.read(court.getId());
		assertEquals(features, courtUpdated.getFeatures());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(featureDao.readAll().size()>0);	
	}
	
	@Test
	public void testReadOne()
	{
		Long id = featureDao.readAll().get(0).getId();
		assertNotNull(featureDao.read(id));
	}
	
	@Test
	public void testDelete()
	{
		List<Feature> features = featureDao.readAll();
		Feature fetureDelete = features.get(features.size()-1);
		Long idDelete = fetureDelete.getId();
		featureDao.delete(fetureDelete);
		assertNull(featureDao.read(idDelete));
	}

	
	public void setFeatureDao(I_FeatureDao featureDaoDao)
	{
		this.featureDao=featureDaoDao;
	}

	public void setSportFacilityDao(I_SportFacilityDao sportFacilityDao)
	{
		this.sportFacilityDao=sportFacilityDao;
	}
	
	public void setCourtDao(I_CourtDao courtDao)
	{
		this.courtDao=courtDao;
	}
}
