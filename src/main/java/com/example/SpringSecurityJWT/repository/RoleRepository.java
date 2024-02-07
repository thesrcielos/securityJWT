package com.example.SpringSecurityJWT.repository;


import com.example.SpringSecurityJWT.model.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity,Integer> {

}
