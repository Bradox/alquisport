package es.tresw.db.dao;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import junit.framework.TestCase;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.operation.DatabaseOperation;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
import es.tresw.db.embeddable.BankAccount;
import es.tresw.db.embeddable.ContactInfo;
import es.tresw.db.entities.Administrator;
import es.tresw.db.entities.Company;
import es.tresw.db.entities.Province;
import es.tresw.db.entities.SportFacility;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;

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
	@Autowired
	private I_UserDao userDao;

	
	@Before
	public void setUp()
	{
		
		administratorDao.getSession().doWork(new Work() {
			
			@Override
			public void execute(Connection connection) throws SQLException {
				
				// initialize database connection here
		        IDatabaseConnection conn;
				try
				{
					conn = new DatabaseConnection(connection);
				
			        // initializedataset here
			        URL url = this.getClass().getResource("administrator.xml");
			        File dataSetFile = new File(url.getFile());
			        
			        IDataSet dataSet = new FlatXmlDataSetBuilder().build(dataSetFile);
			        // ...
			        try
			        {
			            DatabaseOperation.CLEAN_INSERT.execute(conn, dataSet);
			        }
			        finally
			        {
			            connection.close();
			        }
				
				}
				catch (DatabaseUnitException e) 
				{
					// TODO: handle exception
				} 
				catch (MalformedURLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}});

	}
	
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
		address.setMunicipality(province.getMunicipalities().iterator().next());
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
		Date d = null;
		java.util.Calendar cal = GregorianCalendar.getInstance();
		cal.set(1900 + 81, 3, 20);
		d = cal.getTime();
		administrator.setBirthDate(d);
		administrator.setFirstLastName("Alves");
		administrator.setUsername("Brato1982"+lDateTime);
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
		criteria.add(Restrictions.eq("username", "Brato1982"+lDateTime));
		Administrator administratorInserted = (Administrator) criteria.list().get(0);
		assertNotNull(administratorInserted);
	}
	
	@Test
	public void testUpdate()
	{
		SportFacility sportFacility = sportFacilityDao.readAll().get(0);
		List<Administrator> administrators = administratorDao.readAll();
		Administrator admin = administrators.get(administrators.size()-1);
	//	admin.setSportFacility(sportFacility);
		administratorDao.update(admin);
		Administrator adminUpdated = administratorDao.read(admin.getId());
		//assertEquals(admin.getSportFacility(), adminUpdated.getSportFacility());
	}
	
	@Test
	public void testReadAll()
	{
		assertNotNull(administratorDao.readAll());
		assertNotNull(userDao.readAll());
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

	@Test
	public void  removeUserEntity()
	{
		List<Administrator> administrators = administratorDao.readAll();
		Administrator administratorDelete = administrators.get(administrators.size()-1);
		Long idDelete = administratorDelete.getId();
		administratorDao.delete(administratorDelete);
		assertNull(administratorDao.read(idDelete));		
	}
	
	@Test
	public void  listUserEntity()
	{
		
	}
	
	@Test
	public void addUserEntity()
	{
		
	}
	
	@Test
    public void updateUserEntity()
	{
		
	}
	
	@Test
    public void  getUserEntityByID()
	{
		
	}
	
	@Test
    public void  findByName()
	{
		
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

	public void setUserDao(I_UserDao userDao) 
	{
		this.userDao = userDao;
	}
	
	
}
