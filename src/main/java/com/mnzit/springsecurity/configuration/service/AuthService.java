/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.springsecurity.configuration.service;

import com.mnzit.springsecurity.entity.Role;
import com.mnzit.springsecurity.entity.User;
import com.mnzit.springsecurity.repository.RoleRepository;
import com.mnzit.springsecurity.repository.UserRepository;
import com.mnzit.springsecurity.repository.UserRoleRepository;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mnzit
 */
@Service
public class AuthService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        
        if (user != null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roles = roleRepository.getRolesByUserId(user.getId());
        
        Set<GrantedAuthority> grantedAuthority = new HashSet<>();
        
        for (Role role : roles) {
            grantedAuthority.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        
        UserDetails userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthority);
        return userDetail;
        
    }
    
}
