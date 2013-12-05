package com.otkmnb.sample.repository;

import org.springframework.data.mirage.repository.JdbcRepository;

import com.otkmnb.sample.dto.User;

public interface UserRepository extends JdbcRepository<User, Integer>{
}
