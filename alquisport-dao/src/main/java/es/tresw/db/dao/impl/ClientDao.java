package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.beans.Client;
import es.tresw.db.dao.I_ClientDao;

@Transactional
@Repository("clientDao")
public class ClientDao extends GenericDao<Client, Long> implements  I_ClientDao {


}
