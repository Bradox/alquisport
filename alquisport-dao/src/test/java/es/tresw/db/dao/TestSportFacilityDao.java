package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.Appearance;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.Day;
import es.tresw.db.entities.Feature;
import es.tresw.db.entities.Image;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;
import es.tresw.db.entities.SportFacilityMember;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TestSportFacilityDao{
	

	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	@Autowired
	private I_ProvinceDao provinceDao;
	@Autowired
	private I_ClientDao clientDao;
	@Autowired 
	private I_FeatureDao featureDao;
	@Autowired
	private I_ImageDao imageDao;
	
	@Test
	@Rollback(false)
	public void testCreate()
	{
		try
		{
			Province province = provinceDao.read(new Long(1));
			Address address = new Address();
			address.setAddress("Mi Casa");
			address.setType("Calle");
			address.setZipCode("asdas");
			address.setProvince(province);
			address.setMunicipality(province.getMunicipalities().get(0));
			SportFacility sportFacility = new SportFacility();
			sportFacility.setAddress(address);
			Appearance appearance = new Appearance();
			appearance.setColor1("1");
			appearance.setColor2("1");
			appearance.setColor3("1");
			sportFacility.setAppearance(appearance);
			long lDateTime = new Date().getTime();
			ContactInfo contactInfo = new ContactInfo("alejandro.alvaes@gmail.com"+lDateTime, "954417070", "665787878");
			sportFacility.setContactInfo(contactInfo);	
			sportFacility.setDescription("tenemos las mejores pistas y mÃ¡s guapas");
			Feature feature = new Feature();
			feature.setKey("Sergio");
			feature.setPosition(1);
			feature.setValue("PUTA");
			List<Feature> features = new ArrayList<Feature>();
			features.add(feature);
			featureDao.create(feature);
			sportFacility.setFeatures(features);
			sportFacility.setGetHere("por mar tierra o aire");
			BufferedImage originalImage = ImageIO.read(new File("../alquisport-dao/src/test/resources/google_logo_41.png"));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write( originalImage, "jpg", baos );
			baos.flush();
			byte[] imageInByte = baos.toByteArray();
			baos.close();
			Image image = new Image();
			image.setImage(imageInByte);
			image.setDescription("Imagen guapa");
			image.setDiscPath("/sdad/casad/casad");
			image.setHeight(100);
			image.setWeight(100);
			image.setName("name");
			imageDao.create(image);
			List<Image> images=new ArrayList<Image>();
			images.add(image);
			sportFacility.setImages(images);
			sportFacility.setName("Entidad guapa");
			sportFacility.setState(1);
			sportFacility.setUrlName("sergiomarica");
			sportFacilityDao.create(sportFacility);
			assertNotNull(sportFacilityDao.read(sportFacility.getId()));
		}
		catch (Exception e)
		{
			fail(e.toString());
		}
	}
	
	@Test
	public void testUpdate()
	{
		SportFacility sportFacility = sportFacilityDao.readAll().get(0);
		List<Client> clients = clientDao.readAll();
		if(clients.size()>0)
		{
			SportFacilityMember sportFacilityMember = new SportFacilityMember();
			sportFacilityMember.setClient(clients.get(0));
			sportFacilityMember.setSportFacility(sportFacility);
			List<SportFacilityMember> members = new ArrayList<SportFacilityMember>();
			members.add(sportFacilityMember);
			sportFacility.setMembers(members);
		}
		sportFacilityDao.update(sportFacility);
		SportFacility sportFacilityUpdated = sportFacilityDao.read(sportFacility.getId());
		assertEquals(sportFacility.getMembers(), sportFacilityUpdated.getMembers());
	}
	
	
	@Test
	public void testReadAll()
	{
		assertTrue(sportFacilityDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = sportFacilityDao.readAll().get(0).getId();
		assertNotNull(sportFacilityDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		SportFacility sportFacility = sportFacilityDao.readAll().get(0);
		Long id = sportFacility.getId();
		sportFacilityDao.delete(sportFacility);
		assertNull(sportFacilityDao.read(id));		
	}

	
	public void setSportFacility(I_SportFacilityDao sportFacilityDao)
	{
		this.sportFacilityDao=sportFacilityDao;
	}
	
	public void setProvinceDao(I_ProvinceDao provinceDao)
	{
		this.provinceDao=provinceDao;
	}

	public void setClientDao(I_ClientDao clientDao)
	{
		this.clientDao=clientDao;
	}

	public void setFeatureDao(I_FeatureDao featureDao) 
	{
		this.featureDao = featureDao;
	}
	
	
}
