package es.tresw.db.dao;

import es.tresw.db.entities.User;

public interface I_UserDao extends I_GenericDao<User, Long> 
{
<<<<<<< HEAD
=======
	public void removeUserEntity(Integer id);
    public List<User> listUserEntity() ;
    public void addUserEntity(User user) ;
    public void updateUserEntity(User user) ;
>>>>>>> ee7f079ffb5a0e7192151193bf0767e4b08ccf31
    public User findByName(String username);
}
