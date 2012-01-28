package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_ClientDao;
import es.tresw.db.entities.Client;

@Repository("clientDao")
public class ClientDao extends GenericDao<Client, Long> implements  I_ClientDao {

}
