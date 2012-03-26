package es.tresw.db.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.constants.PisteaConstants;
import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Client;
import es.tresw.db.entities.Province;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestClientDao{
	

	@Autowired
	private I_ClientDao clientDao;
	@Autowired
	private I_ProvinceDao provinceDao;

	@SuppressWarnings("deprecation")
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() {
		long lDateTime = new Date().getTime();
		Province province = provinceDao.read(new Long(1));
		Address address = new Address();
		address.setZipCode("asasd");
		address.setAddress("Mi Casa");
		address.setType("Calle");
		address.setProvince(province);
		address.setMunicipality(province.getMunicipalities().iterator().next());
		Client client = new Client();
		client.setAddress(address);
		BankAccount bankAccount=new BankAccount();
		bankAccount.setAccountNumber(1234567898);
		bankAccount.setControlCode(12);
		bankAccount.setEntityCode(1234);
		bankAccount.setOfficeCode(1234);
		client.setBankAccount(bankAccount);
		client.setEnabled(true);
		ContactInfo contactInfo = new ContactInfo("alejandro.alvaes@gmail.com"+lDateTime, "954417070", "665787878");
		client.setContactInfo(contactInfo);
		client.setBirthDate(new Date(1981, 3, 20));
		client.setFirstLastName("Alves");
		client.setUsername("Brato1982"+lDateTime);
		client.setName("Alejandro");
		client.setPassword("123123");
		client.setSecondLastName("Calderon");
		clientDao.create(client);
		Criteria criteria = clientDao.getSession().createCriteria(Client.class);
		criteria.add(Restrictions.eq("login", "Brato1982"+lDateTime));
		/*Role role = roleDao.read(new Long (1));
		List<Role> lista = new ArrayList<Role>();
		lista.add(role);
		client.setRoles(lista);*/
		Client clientInserted = (Client) criteria.list().get(0);
		assertNotNull(clientInserted);
	}
	
	
	@Test
	public void testReadAll()
	{
		assertNotNull(clientDao.readAll());
	}
	
	@Test
	public void testReadOne()
	{
		Long id = clientDao.readAll().get(0).getId();
		assertNotNull(clientDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Client clientDelete = clientDao.readAll().get(0);
		Long idDelete = clientDelete.getId();
		clientDao.delete(clientDelete);
		assertNull(clientDao.read(idDelete));
	}

	@Test
	@Transactional
	@Rollback(true)
	public void testReadByField()
	{
		String login = clientDao.readAll().get(0).getUsername();
		List<String> fields = new ArrayList<String>();
		fields.add("login"); 
		List<String> expressions = new ArrayList<String>();
		expressions.add(PisteaConstants.EQUALS);
		List<String> values = new ArrayList<String>();
		values.add(login);
		List<String> types = new ArrayList<String>();
		types.add("String");
		List<Client> clients = clientDao.readByField(fields, expressions, values, types);
		assertEquals(1,clients.size());
	}
	
	
	public void setClientDao(I_ClientDao clientDao)
	{
		this.clientDao=clientDao;
	}

	public void setProvinceDao(I_ProvinceDao provinceDao)
	{
		this.provinceDao=provinceDao;
	}
	
}
