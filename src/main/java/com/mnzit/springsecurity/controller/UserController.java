/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.springsecurity.controller;

import com.mnzit.springsecurity.entity.Role;
import com.mnzit.springsecurity.entity.User;
import com.mnzit.springsecurity.repository.RoleRepository;
import com.mnzit.springsecurity.repository.UserRepository;
import com.mnzit.springsecurity.repository.UserRoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Mnzit
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @GetMapping()
    public List<User> index() {
        return userRepository.findAll();
    }

    @GetMapping("role")
    public List<Role> roles() {
        return roleRepository.findAll();
    }

    @GetMapping("roles/{userid}")
    public List<Role> userRoles(@PathVariable("userid") Long userId) {
        return roleRepository.getRolesByUserId(userId);
    }

    @GetMapping("/user/{username}")
    public User user(@PathVariable("username") String username) {
        return userRepository.findByUserName(username);
    }

}
