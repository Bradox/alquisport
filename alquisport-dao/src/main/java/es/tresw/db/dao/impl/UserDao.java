package es.tresw.db.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import es.tresw.db.dao.I_UserDao;
import es.tresw.db.entities.User;

@Repository("userDao")
public class UserDao extends GenericDao<User, Long> implements  I_UserDao {
	
    public void addUserEntity(User user) 
    {
        try 
        {
        	sessionFactory.getCurrentSession().save(user);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    public User findByName(String username) 
    {
    	Criteria criteria = getSession().createCriteria(User.class);
    	criteria.add(Restrictions.eq("username", username));
    	return (User)criteria.uniqueResult();
    }

    
    public void updateUserEntity(User user) 
    {
        try 
        {
        	sessionFactory.getCurrentSession().update(user);
        } 
        catch (Exception e) 
        {
            System.out.println(e);
        }
    }

    @SuppressWarnings("unchecked")
	public List<User> listUserEntity() 
    {
    	return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    public void removeUserEntity(Integer id)
    {
        User user = (User) sessionFactory.getCurrentSession().load(User.class, id);
        
        if (null != user)
        {
            sessionFactory.getCurrentSession().delete(user);
        }
    }


}
