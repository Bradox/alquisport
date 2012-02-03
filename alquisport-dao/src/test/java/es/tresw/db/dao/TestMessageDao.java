package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Client;
import es.tresw.db.entities.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestMessageDao{
	

	@Autowired
	private I_MessageDao messageDao;
	@Autowired
	private I_ClientDao clientDao;

	@Test
	@Rollback(false)
	public void testCreate() 
	{
		List<Client> clients = clientDao.readAll();
		Client clientFrom = clients.get(0);
		Client clientTo = clients.get(0);
		Message message= new Message();
		message.setDateRead(new Date());
		message.setDateSend(new Date());
		message.setState(1);
		message.setSubject("PUTAAAAAA!!!");
		message.setText("Eres una putaaaaaaa");
		message.setUserFrom(clientFrom);
		message.setUserTo(clientTo);
		messageDao.create(message);
		Message messageInserted = messageDao.read(message.getId());
		assertNotNull(messageInserted);
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(messageDao.readAll().size()>0);	
	}
	
	@Test
	public void testReadOne()
	{
		Long id = messageDao.readAll().get(0).getId();
		assertNotNull(messageDao.read(id));
	}
	
	@Test
	public void testDelete()
	{
		List<Message> messages = messageDao.readAll();
		Message messageDelete = messages.get(messages.size()-1);
		Long idDelete = messageDelete.getId();
		messageDao.delete(messageDelete);
		assertNull(messageDao.read(idDelete));
	}

	
	public void setMessageDao(I_MessageDao messageDao)
	{
		this.messageDao=messageDao;
	}

	public void setClientDao(I_ClientDao clientDao)
	{
		this.clientDao=clientDao;
	}

}
