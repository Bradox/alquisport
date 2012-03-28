package es.tresw.db.dao;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestRoleDao
{

	@Autowired
	private I_RoleDao roleDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Role role = new Role();
		role.setName("CLIENT");
		roleDao.create(role);
		assertNull(roleDao.read(role.getId()));
 	}
	
	@Test
	public void testUpdate()
	{
		String name ="CLIENT"+new Date().getTime();
		Role role = roleDao.readAll().get(0);
		role.setName(name);
		roleDao.create(role);
		Role roleAux = roleDao.read(role.getId());
		assertEquals(name, roleAux.getName());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(roleDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = roleDao.readAll().get(0).getId();
		assertNotNull(roleDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Role role = roleDao.readAll().get(0);
		roleDao.delete(role);
		assertNull(roleDao.read(role.getId()));
	}

	public void setRoleDao(I_RoleDao roleDao) 
	{
		this.roleDao = roleDao;
	}
	
}
