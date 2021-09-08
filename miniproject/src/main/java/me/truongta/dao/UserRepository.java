package me.truongta.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.truongta.entity.Users;

public interface UserRepository extends JpaRepository<Users, String> {

}