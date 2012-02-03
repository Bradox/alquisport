package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_CompanyDao;
import es.tresw.db.entities.Company;

@Repository("companyDao")
public class CompanyDao extends GenericDao<Company, Long> implements  I_CompanyDao 
{
	
}
