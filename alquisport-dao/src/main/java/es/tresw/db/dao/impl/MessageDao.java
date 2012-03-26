package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_MessageDao;
import es.tresw.db.entities.Message;

@Repository("messageDao")
public class MessageDao extends GenericDao<Message, Long> implements  I_MessageDao {

}
