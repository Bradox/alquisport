package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_AuditTrailDao;
import es.tresw.db.entities.AuditTrail;

@Repository("auditTrailDato")
public class AuditTrailDao extends GenericDao<AuditTrail, Long> implements  I_AuditTrailDao {

}
