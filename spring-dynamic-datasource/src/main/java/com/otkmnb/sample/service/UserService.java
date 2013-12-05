package com.otkmnb.sample.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.otkmnb.sample.dto.User;

public interface UserService {
    
    @Transactional
    List<User> findAll();

}
