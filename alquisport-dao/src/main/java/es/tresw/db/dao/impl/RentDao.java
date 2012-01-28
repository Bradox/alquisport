package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_RentDao;
import es.tresw.db.entities.Rental;


@Repository("rentDao")
public class RentDao extends GenericDao<Rental, Long> implements  I_RentDao {

}
