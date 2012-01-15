package es.tresw.db.dao;

import java.util.Date;
import java.util.List;


import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.AuditTrail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class TestAuditTrailDao  extends AbstractTransactionalJUnit4SpringContextTests 
{
	

	@Autowired
	private I_AuditTrailDao auditTrailDao;
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		AuditTrail auditTrail = new AuditTrail();
		auditTrail.setDate(new Date());
		auditTrail.setDescription("Sergio es marica");
		auditTrailDao.create(auditTrail);
		List<AuditTrail> auditTrails = auditTrailDao.readAll();
		assertNotNull(auditTrails.get(auditTrails.size()-1));
	}
	
	@Test
	public void testUpdate()
	{
		List<AuditTrail> auditTrails = auditTrailDao.readAll();
		AuditTrail auditTrailUpdate = auditTrails.get(auditTrails.size()-1);
		auditTrailUpdate.setDescription("Sergio es muy marica");
		auditTrailDao.update(auditTrailUpdate);
		auditTrails = auditTrailDao.readAll();
		auditTrailUpdate = auditTrails.get(auditTrails.size()-1);
		assertEquals("Sergio es muy marica", auditTrailUpdate.getDescription());
	}
	
	@Test
	public void testReadAll()
	{
		assertNotNull(auditTrailDao.readAll());
	}
	
	@Test
	public void testReadOne()
	{
		List<AuditTrail> auditTrails = auditTrailDao.readAll();
		long id = auditTrails.get(auditTrails.size()-1).getId();
		assertNotNull(auditTrailDao.read(id));
	}
	
	@Test
	@Transactional
	@Rollback(false)
	public void testDelete()
	{
		List<AuditTrail> auditTrails = auditTrailDao.readAll();
		AuditTrail auditTrailDelete = auditTrails.get(auditTrails.size()-1);
		auditTrailDao.delete(auditTrailDelete);
		assertNull(auditTrailDao.read(auditTrailDelete.getId()));
	}

	

	public void setAuditTrailDao(I_AuditTrailDao auditTrailDao)
	{
		this.auditTrailDao=auditTrailDao;
	}
}
