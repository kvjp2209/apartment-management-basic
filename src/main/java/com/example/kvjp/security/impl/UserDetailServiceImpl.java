//package com.example.kvjp.security.impl;
//
//import com.example.kvjp.model.Account;
//import com.example.kvjp.repository.AccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
////public class UserDetailServiceImpl implements UserDetailsService {
////    @Autowired
////    AccountRepository accountRepository;
////
////    @Override
////    @Transactional
////    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
////        Account account = accountRepository.findByUsername(username)
////                .orElseThrow(() -> new UsernameNotFoundException("Not fount this account!!!"));
////        return UserDetailsImpl.build(account);
////    }
////}
