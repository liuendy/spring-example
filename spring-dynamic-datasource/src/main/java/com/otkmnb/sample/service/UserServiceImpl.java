package com.otkmnb.sample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.otkmnb.sample.dto.User;
import com.otkmnb.sample.exception.NotFoundUserException;
import com.otkmnb.sample.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> findAll() {
        List<User> users = Lists.newArrayList(repository.findAll());
        if (users.isEmpty()) {
            throw new NotFoundUserException("ユーザが見つかりません");
        }
        return users;
    }

}
