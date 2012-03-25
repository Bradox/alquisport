package es.tresw.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Role;

public class Assembler {

  @Transactional(readOnly = true)
  User buildUserFromUserEntity(es.tresw.db.entities.User userEntity) {

    String username = userEntity.getusername();
    String password = userEntity.getPassword();
    boolean enabled = userEntity.getEnabled();
    boolean accountNonExpired = userEntity.getEnabled();
    boolean credentialsNonExpired = userEntity.getEnabled();
    boolean accountNonLocked = userEntity.getEnabled();

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (Role role : userEntity.getRoles()) {
      authorities.add(new GrantedAuthorityImpl(role.getName()));
    }

    User user = new User(username, password, enabled,
    	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    return user;
  }
}
