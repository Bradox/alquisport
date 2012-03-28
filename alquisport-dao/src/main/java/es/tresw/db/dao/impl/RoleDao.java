package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_RoleDao;
import es.tresw.db.entities.Role;

@Repository("roleDao")
public class RoleDao extends GenericDao<Role, Long> implements I_RoleDao
{


}
