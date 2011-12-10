package es.tresw.db.dao.impl;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_AdministratorDao;
import es.tresw.db.entities.Administrator;

@Repository("administratorDao")
public class AdministratorDao extends GenericDao<Administrator, Long> implements  I_AdministratorDao {

}
