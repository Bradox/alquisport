package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set; 
import javax.imageio.ImageIO;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

import junit.framework.Assert;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.method.MethodConstraintViolation;
import org.hibernate.validator.method.MethodConstraintViolationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.Appearance;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.Feature;
import es.tresw.db.entities.Image;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;
import es.tresw.db.entities.SportFacilityMember;
import es.tresw.db.entities.Zone;
import javax.validation.Validator;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestSportFacilityDao{
	

	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	@Autowired
	private I_ProvinceDao provinceDao;
	@Autowired
	private I_ClientDao clientDao;
	@Autowired
	private I_ImageDao imageDao;
	@Autowired
	private I_ZoneDao zoneDao;
	@Autowired
	private Validator validator;
	 
	
	@Test
	@Rollback(false)
	public void testCreateFail()
	{
		SportFacility sf = new SportFacility();
		Set<ConstraintViolation<SportFacility>> constraintViolations = validator.validate(sf);
		sf.setAddress(new Address());
		if(constraintViolations.size() > 0)
		{
			Iterator<ConstraintViolation<SportFacility>> iterator = constraintViolations.iterator();
			while(iterator.hasNext())
				{
					ConstraintViolation<SportFacility> cv = iterator.next();
					System.out.println(cv.getMessage());
					System.out.println(cv.getPropertyPath());
				}
			}		
			
			Set<ConstraintViolation<Address>> constraintViolations1 = validator.validate(sf.getAddress());
	
			if(constraintViolations1.size() > 0)
			{
				Iterator<ConstraintViolation<Address>> iterator = constraintViolations1.iterator();
				while(iterator.hasNext())
				{
					ConstraintViolation<Address> cv = iterator.next();
					System.out.println(cv.getMessage());
					System.out.println(cv.getPropertyPath());
				}
			}		
			
		try {
		    sportFacilityDao.create(sf);
		    sportFacilityDao.getSession().flush();
		} catch (MethodConstraintViolationException e) {
			Iterator<MethodConstraintViolation<?>> it =e.getConstraintViolations().iterator();
			while(it.hasNext())
				it.next().getPropertyPath();
		}
	}	

	@Test
	@Rollback(false)
	public void testCreateSuccess()
	{
		SportFacility sf = new SportFacility();
		sf.setName("Name");
		sf.setUrlName("asdasdasdasd");
		sf.setState(1);
		ContactInfo ci = new ContactInfo();
		ci.setTelephone1("asdasdasd");
		ci.setEmail("asdad@asdsad.com" + new Date().getTime());
		sf.setContactInfo(ci);
		
		Address a = new Address();
		a.setAddress("asdasd sdas d");
		a.setZipCode("41013");
		
		Province p = provinceDao.read(new Long(1));
		a.setProvince(p);
		Municipality m = p.getMunicipalities().iterator().next();
		a.setMunicipality(m);
		Zone zone = new Zone();
		zone.setMunicipality(m);
		zone.setName("zona1");
		zoneDao.create(zone);
		a.setZone(zone);
		sf.setAddress(a);
		try
		{
			sportFacilityDao.create(sf);
		}
		catch (ConstraintViolationException e) 
		{
			assertEquals(1, e.getConstraintViolations().size());
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
			Set<SportFacilityMember> members = new HashSet<SportFacilityMember>();
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
	
	public void setZoneDao(I_ZoneDao zoneDao)
	{
		this.zoneDao=zoneDao;
	}
	
	public void setValidator(Validator validator)
	{
		this.validator=validator;
	}
}
