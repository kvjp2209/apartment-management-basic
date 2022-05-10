//package com.example.kvjp.security.impl;
//
//import com.example.kvjp.model.Account;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.Set;
//
//public class UserDetailsImpl implements UserDetails {
//    private static final long serialVersionUID = 1L;
//
//    private Long id;
//
//    private String username;
//
//    @JsonIgnore
//    private String password;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    public UserDetailsImpl(Long id, String username, String password) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//    }
//
//    public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.authorities = authorities;
//    }
//
//    public static UserDetailsImpl build(Account account) {
//        Collection<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//
//        return new UserDetailsImpl(
//                account.getId(),
//                account.getUsername(),
//                account.getPassword(),
//                authorities
//        );
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        // Mặc định mình sẽ để tất cả là ROLE_USER. Để demo cho đơn giản.
//        return Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"));
//    }
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        return username;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
