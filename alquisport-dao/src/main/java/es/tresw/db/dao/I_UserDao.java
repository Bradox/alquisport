package es.tresw.db.dao;

import es.tresw.db.entities.User;

public interface I_UserDao extends I_GenericDao<User, Long> 
{
    public User findByName(String username);
}
