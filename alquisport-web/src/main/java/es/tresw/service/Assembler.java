package es.tresw.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.annotation.Transactional;

import es.tresw.db.entities.Role;
import es.tresw.db.entities.UserRole;

public class Assembler {

  @Transactional(readOnly = true)
  User buildUserFromUserEntity(es.tresw.db.entities.User userEntity) {

    String username = userEntity.getUsername();
    String password = userEntity.getPassword();
    boolean enabled = userEntity.getEnabled();
    boolean accountNonExpired = userEntity.isAccountNonExpired();
    boolean credentialsNonExpired = userEntity.isCredentialsNonExpired();
    boolean accountNonLocked = userEntity.isAccountNonLocked();

    Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    for (UserRole userRole : userEntity.getUserRoles()) {
      authorities.add(new GrantedAuthorityImpl(userRole.getRole().getName()));
    }

    User user = new User(username, password, enabled,
    	      accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);

    return user;
  }
}
