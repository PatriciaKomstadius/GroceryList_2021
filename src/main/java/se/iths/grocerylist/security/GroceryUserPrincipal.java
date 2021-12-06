package se.iths.grocerylist.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import se.iths.grocerylist.entity.RoleEntity;
import se.iths.grocerylist.entity.UserEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class GroceryUserPrincipal implements UserDetails {

    private UserEntity userEntity;
    public GroceryUserPrincipal(UserEntity userEntity){
        super();
        this.userEntity = userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        RoleEntity role = userEntity.getRole();
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>(1);
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRoleName().toUpperCase()));

        return grantedAuthorities;
    }


    @Override
    public String getPassword() {
        return this.userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
