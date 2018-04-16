package com.andy.oauth2.role.dao;

import com.andy.oauth2.role.entity.SRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SRoleRepository extends JpaRepository<SRole,Integer> {



}