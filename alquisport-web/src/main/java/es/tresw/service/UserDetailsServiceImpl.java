package es.tresw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.dao.I_UserDao;
import es.tresw.db.entities.User;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired private I_UserDao dao;
  @Autowired private Assembler assembler;

  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String username)
      throws DataAccessException {
	  User u = dao.findByName(username);
	  System.out.println("username="+u);
	  if (u == null)
		  throw new UsernameNotFoundException("El usuario no existe");
	  return assembler.buildUserFromUserEntity(u);
  }

	public I_UserDao getDao() {
		return dao;
	}
	
	public void setDao(I_UserDao dao) {
		this.dao = dao;
	}
	
	public Assembler getAssembler() {
		return assembler;
	}
	
	public void setAssembler(Assembler assembler) {
		this.assembler = assembler;
	}
  
}

