package com.example.storeelectronic.demo.Services;

import com.example.storeelectronic.demo.Repository.UserRepository;
import com.example.storeelectronic.demo.entities.User;
import com.example.storeelectronic.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomuserDetail implements UserDetailsService {
    @Autowired
    private UserRepository userrepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return this.userrepo.findByEmail(email).orElseThrow(()->new ResourceNotFoundException());


    }
}
