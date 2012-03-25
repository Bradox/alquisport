package es.tresw.db.dao.impl;

import org.springframework.stereotype.Repository;
import es.tresw.db.dao.I_UserRoleDao;
import es.tresw.db.entities.UserRole;

@Repository("userRoleDao")
public class UserRoleDao extends GenericDao<UserRole, Long> implements  I_UserRoleDao 
{

	


}
