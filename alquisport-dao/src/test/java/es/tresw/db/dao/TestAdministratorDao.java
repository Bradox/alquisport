package es.tresw.db.dao;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.embeddable.Address;
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Company;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestAdministratorDao extends TestCase{
	

	@Autowired
	private I_AdministratorDao administratorDao;
	@Autowired
	private I_ProvinceDao provinceDao;
	@Autowired
	private I_SportFacilityDao sportFacilityDao;
	@Autowired
	private I_CompanyDao companyDao;
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() {
		long lDateTime = new Date().getTime();
		Province province = provinceDao.read(new Long(1));
		Address address = new Address();
		address.setAddress("Mi Casa");
		address.setType("Calle");
		address.setProvince(province);
		address.setMunicipality(province.getMunicipalities().get(0));
		Administrator administrator = new Administrator();
		administrator.setAddress(address);
		BankAccount bankAccount=new BankAccount();
		bankAccount.setAccountNumber(1234567898);
		bankAccount.setControlCode(12);
		bankAccount.setEntityCode(1234);
		bankAccount.setOfficeCode(1234);
		administrator.setBankAccount(bankAccount);
		administrator.setEnabled(true);
		ContactInfo contactInfo = new ContactInfo("alejandro.alvaes@gmail.com"+lDateTime, "954417070", "665787878");
		administrator.setContactInfo(contactInfo);
		administrator.setBirthDate(new Date(1981, 3, 20));
		administrator.setFirstLastName("Alves");
		administrator.setLogin("Brato1982"+lDateTime);
		administrator.setName("Alejandro");
		administrator.setPassword("123123");
		administrator.setSecondLastName("Calderon");
		Company company = new Company();
		company.setAddress(address);
		company.setName("hola");
		company.setCIF("111111111");
		companyDao.create(company);
		administrator.setCompany(company);
		administratorDao.create(administrator);
		Criteria criteria = administratorDao.getSession().createCriteria(Administrator.class);
		criteria.add(Restrictions.eq("login", "Brato1982"+lDateTime));
		Administrator administratorInserted = (Administrator) criteria.list().get(0);
		assertNotNull(administratorInserted);
	}
	
	@Test
	public void testUpdate()
	{
		SportFacility sportFacility = sportFacilityDao.readAll().get(0);
		List<Administrator> administrators = administratorDao.readAll();
		Administrator admin = administrators.get(administrators.size()-1);
		admin.setSportFacility(sportFacility);
		administratorDao.update(admin);
		Administrator adminUpdated = administratorDao.read(admin.getId());
		assertEquals(admin.getSportFacility(), adminUpdated.getSportFacility());
	}
	
	@Test
	public void testReadAll()
	{
		assertNotNull(administratorDao.readAll());
	}
	
	@Test
	public void testReadOne()
	{
		Long id = administratorDao.readAll().get(0).getId();
		assertNotNull(administratorDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		List<Administrator> administrators = administratorDao.readAll();
		Administrator administratorDelete = administrators.get(administrators.size()-1);
		Long idDelete = administratorDelete.getId();
		administratorDao.delete(administratorDelete);
		assertNull(administratorDao.read(idDelete));
	}

	
	public void setClientDao(I_AdministratorDao administratorDao)
	{
		this.administratorDao=administratorDao;
	}

	public void setProvinceDao(I_ProvinceDao provinceDao)
	{
		this.provinceDao=provinceDao;
	}
	
	public void setSportFacility(I_SportFacilityDao sportFacilityDao)
	{
		this.sportFacilityDao=sportFacilityDao;
	}
	
	public void setCompanyDao(I_CompanyDao companyDao)
	{
		this.companyDao=companyDao;
	}
}
