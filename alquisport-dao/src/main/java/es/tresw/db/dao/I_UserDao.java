package es.tresw.db.dao;

import java.util.List;

import es.tresw.db.entities.User;

public interface I_UserDao extends I_GenericDao<User, Long> 
{
	public void removeUserEntity(Integer id);
    public List<User> listUserEntity() ;
    public void addUserEntity(User user) ;
    public void updateUserEntity(User user) ;
    public User findByName(String username);
}
