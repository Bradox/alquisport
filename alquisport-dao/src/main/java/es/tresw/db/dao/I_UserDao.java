package es.tresw.db.dao;

import java.util.List;

import es.tresw.db.entities.User;

public interface I_UserDao extends I_GenericDao<User, Long> 
{
    public User findByName(String username);
}
