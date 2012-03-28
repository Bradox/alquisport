package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ProvinceDao;
import es.tresw.db.entities.Province;


@Repository("provinceDao")
public class ProvinceDao extends GenericDao<Province, Long> implements  I_ProvinceDao {
	/*
	@PostConstruct
	private void populate()
	{
		sessionFactory.getCurrentSession().doWork(
				 new Work() {
				        public void execute(Connection connection) throws SQLException 
				        { 
				    		ResourceDatabasePopulator rdp = new ResourceDatabasePopulator();
				    		rdp.addScript(new ClassPathResource("/script-file.sql"));
				    		rdp.populate(connection);
				        }
				    }		
				
		);
	}*/
	
}
