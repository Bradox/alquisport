package es.tresw.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Authority;

public class Assembler {

  @Transactional(readOnly = true)
  User buildUserFromUserEntity(es.tresw.db.entities.User userEntity) {

    String username = userEntity.getUsername();
    String password = userEntity.getPassword();
    boolean enabled = userEntity.isActive();
    boolean accountNonExpired = userEntity.isActive();
    boolean credentialsNonExpired = userEntity.isActive();
    boolean accountNonLocked = userEntity.isActive();

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (Authority role : userEntity.getAuthorities()) {
      authorities.add(new GrantedAuthorityImpl(role.getName()));
    }

    User user = new User(username, password, enabled,
    	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    return user;
  }
}