package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.SportFacility;
import es.tresw.db.entities.SportFacilityMember;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestSportFacilityMember {

	@Autowired
	private I_SportFacilityMemberDao sportFacilityMemberDao;
	@Autowired
	private I_ClientDao clientDao;
	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		SportFacility sportFacility = sportFacilityDao.readAll().get(0);
		Client client = clientDao.readAll().get(0);
		SportFacilityMember sportFacilityMember = new SportFacilityMember();
		sportFacilityMember.setClient(client);
		sportFacilityMember.setSportFacility(sportFacility);
		sportFacilityMemberDao.create(sportFacilityMember);
		assertNotNull(sportFacilityMemberDao.read(sportFacilityMember.getId()));
	}
	
	@Test
	public void testUpdate()
	{
		SportFacilityMember sportFacilityMember = sportFacilityMemberDao.readAll().get(0);
		List<Client> clients = clientDao.readAll(); 
		Client client = clients.get(clients.size()-1);
		sportFacilityMember.setClient(client);
		sportFacilityMemberDao.create(sportFacilityMember);
		SportFacilityMember sp = sportFacilityMemberDao.read(sportFacilityMember.getId());
		assertEquals(client.getId(), sp.getClient().getId());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(sportFacilityMemberDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		assertNotNull(sportFacilityDao.read(sportFacilityDao.readAll().get(0).getId()));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		SportFacilityMember sportFacilityMember = sportFacilityMemberDao.readAll().get(0);
		sportFacilityMemberDao.delete(sportFacilityMember);
		assertNull(sportFacilityMemberDao.read(sportFacilityMember.getId()));
	}

	public void setSportFacilityMemberDao(
			I_SportFacilityMemberDao sportFacilityMemberDao)
	{
		this.sportFacilityMemberDao = sportFacilityMemberDao;
	}

	public void setClientDao(I_ClientDao clientDao) 
	{
		this.clientDao = clientDao;
	}

	public void setSportFacilityDao(I_SportFacilityDao sportFacilityDao)
	{
		this.sportFacilityDao = sportFacilityDao;
	}


	
	
}
