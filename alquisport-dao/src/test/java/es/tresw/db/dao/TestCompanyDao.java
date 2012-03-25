package es.tresw.db.dao;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.embeddable.Address;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Company;
import es.tresw.db.entities.Municipality;
import es.tresw.db.entities.Province;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:intercambia-servlet-test.xml"})
@Transactional
public class TestCompanyDao 
{
	@Autowired
	private I_CompanyDao companyDao;
	@Autowired
	private I_ProvinceDao provinceDao;
	@Autowired
	private I_AdministratorDao administratorDao;
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void testCreate() 
	{
		Company company = new Company();
		Address address = new Address();
		address.setAddress("Castillo Alcalá de Guadaira nº 15 5ºD");
		Province province = provinceDao.readAll().get(0);
		Municipality municipality = province.getMunicipalities().iterator().next();
		address.setMunicipality(municipality);
		address.setProvince(province);
		address.setType("Calle");
		address.setZipCode("41013");
		address.setZone(municipality.getZones().iterator().next());
		company.setAddress(address);
		Set<Administrator> administrators = new HashSet<Administrator>();
		administrators.add(administratorDao.readAll().get(0));
		company.setAdministrators(administrators);
		company.setCIF("asdasdasd");
		company.setName("XYZ");
		companyDao.create(company);
		assertNotNull(companyDao.read(company.getId()));
		
	}
	
	@Test
	public void testUpdate()
	{
		String name ="Nuevo nombre";
		Company company = companyDao.readAll().get(0);
		company.setName(name);
		companyDao.create(company);
		Company companyAux = companyDao.read(company.getId());
		assertEquals(name, companyAux.getName());
	}
	
	@Test
	public void testReadAll()
	{
		assertTrue(companyDao.readAll().size()>0);
	}
	
	@Test
	public void testReadOne()
	{
		Long id = companyDao.readAll().get(0).getId();
		assertNotNull(companyDao.read(id));
	}
	
	@Test
	@Transactional
	public void testDelete()
	{
		Company company = companyDao.readAll().get(0);
		companyDao.delete(company);
		assertNotNull(companyDao.read(company.getId()));	
	}

	public void setCompanyDao(I_CompanyDao companyDao)
	{
		this.companyDao=companyDao;
	}
	
	public void setProvinceDao(I_ProvinceDao provinceDao)
	{
		this.provinceDao=provinceDao;
	}

	public void setAdministratorDao(I_AdministratorDao administratorDao)
	{
		this.administratorDao=administratorDao;
	}
}

