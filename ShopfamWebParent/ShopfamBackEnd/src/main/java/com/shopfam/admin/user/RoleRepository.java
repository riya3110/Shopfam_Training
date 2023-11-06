package com.shopfam.admin.user;

import org.springframework.data.repository.CrudRepository;

import com.shopfam.common.entity.Role;
public interface RoleRepository extends CrudRepository<Role , Integer> {

}
