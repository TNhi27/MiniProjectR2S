package me.truongta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.truongta.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

}