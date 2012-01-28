package es.tresw.db.dao.impl;

import java.util.List;

import javax.management.Query;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_UserDao;
import es.tresw.db.entities.User;

@Repository("userDao")
public class UserDao extends GenericDao<User, Long> implements  I_UserDao {
	
	public User findByName(String username) {
    	Criteria criteria = getSession().createCriteria(User.class);
    	criteria.add(Restrictions.eq("username", username));
    	return (User)criteria.uniqueResult();
    } 

}
