package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Image;
import es.tresw.db.entities.SportFacility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestImageDao{
	

	@Autowired
	private I_ImageDao imageDao;
	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	
	@Test
	public void testCreate() 
	{
		try
		{
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
			List<SportFacility> sportFacilities = sportFacilityDao.readAll();
			SportFacility sportFacility = sportFacilities.get(0);
			images.addAll(sportFacility.getImagenes());
			sportFacility.setImages(images);
			sportFacilityDao.update(sportFacility);
			SportFacility sportFacilityUpdated = sportFacilityDao.read(sportFacility.getId());
			assertEquals(images, sportFacilityUpdated.getImagenes());
		}
		catch (Exception e) {
			fail(e.toString());
		}
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(imageDao.readAll().size()>0);	
	}
	
	@Test
	public void testReadOne()
	{
		Long id = imageDao.readAll().get(0).getId();
		assertNotNull(imageDao.read(id));
	}
	
	@Test
	public void testDelete()
	{
		List<Image> images = imageDao.readAll();
		Image imageDelete = images.get(images.size()-1);
		Long idDelete = imageDelete.getId();
		imageDao.delete(imageDelete);
		assertNull(imageDao.read(idDelete));
	}

	
	public void setFeatureDao(I_ImageDao imageDao)
	{
		this.imageDao=imageDao;
	}

	public void setSportFacilityDao(I_SportFacilityDao sportFacilityDao)
	{
		this.sportFacilityDao=sportFacilityDao;
	}
	
}
