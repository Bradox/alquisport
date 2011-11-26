package es.tresw.db.dao;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.beans.Administrator;
import es.tresw.db.beans.Client;
import es.tresw.db.dao.impl.AdministratorDao;
import es.tresw.db.dao.impl.ClientDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
public class TestUserDao extends TestCase{
	

	@Autowired
	@Qualifier(value="clientDao")
	private I_ClientDao clientDao;

	@Test
	public void test() {
		Client ad = new Client();
		clientDao.create(ad);
	}
	
	
	private void setClientDao(ClientDao clientDao)
	{
		this.clientDao=clientDao;
	}

}
