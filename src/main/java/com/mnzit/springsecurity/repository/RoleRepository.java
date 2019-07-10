/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mnzit.springsecurity.repository;

import com.mnzit.springsecurity.entity.Role;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Mnzit
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    @Query(value = "SELECT r.* from tbl_roles r JOIN tbl_user_roles ur ON ur.role_id = r.id WHERE ur.user_id=?", nativeQuery = true)
    List<Role> getRolesByUserId(Long id);
}
