package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set; 
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.jdbc.Work;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;
import es.tresw.db.entities.SportFacilityMember;
import es.tresw.db.entities.Zone;


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
	private I_ZoneDao zoneDao;
	@Autowired
	private Validator validator;
	 
	  @Before
	  public void onSetUpInTransaction() throws Exception 
	  {
		  sportFacilityDao.getSession().doWork(new Work() {
				@Override
				public void execute(Connection connection) throws SQLException {
					IDatabaseConnection conn;
				     try {
				      conn = new DatabaseConnection(connection);
				
				      FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
				      IDataSet dataSet=builder.build(this.getClass().getClassLoader().getResourceAsStream("database.xml"));
				      
				      DatabaseOperation.CLEAN_INSERT.execute(conn,dataSet);
				    }
				    catch (Exception e) {
						e.toString();
					}
				}
		  });
	  }


	@Test
	@Rollback(false)
	public void testCreateFail()
	{
		SportFacility sf = new SportFacility();
		sf.setAddress(new Address());
		Set<ConstraintViolation<SportFacility>> constraintViolations = validator.validate(sf);
		assertTrue(constraintViolations.size()==6);
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

