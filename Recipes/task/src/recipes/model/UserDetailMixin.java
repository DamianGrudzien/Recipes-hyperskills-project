package recipes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public interface UserDetailMixin extends UserDetails {
    @JsonIgnore
    @Override
    default Collection<? extends GrantedAuthority> getAuthorities() {
     return Collections.emptyList();
    }
    @JsonIgnore
    @Override
    default String getPassword() {
     return null;
    }
    @JsonIgnore
    @Override
    default String getUsername() {
        return null;
    }
    @JsonIgnore
    @Override
    default boolean isAccountNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    default boolean isAccountNonLocked() {
        return true;
    }
    @JsonIgnore
    @Override
    default boolean isCredentialsNonExpired() {
        return true;
    }
    @JsonIgnore
    @Override
    default boolean isEnabled() {
        return true;
    }
}
