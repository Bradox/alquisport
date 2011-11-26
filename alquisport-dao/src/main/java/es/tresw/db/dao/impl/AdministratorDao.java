package es.tresw.db.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import es.tresw.db.beans.Administrator;
import es.tresw.db.dao.I_AdministratorDao;

@Component("administratorDao")
public class AdministratorDao extends GenericDao<Administrator, Long> implements  I_AdministratorDao {

}
