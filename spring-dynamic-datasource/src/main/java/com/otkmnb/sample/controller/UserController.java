package com.otkmnb.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.otkmnb.sample.dto.User;
import com.otkmnb.sample.resolver.DatasourceType;
import com.otkmnb.sample.resolver.DynamicDatasourceContextHolder;
import com.otkmnb.sample.service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private UserService service;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public String findAll() {
        List<User> users = service.findAll();
        return users.toString();
    }

    @RequestMapping(value = "/users/global", method = RequestMethod.GET)
    @ResponseBody
    public String findAllForGlobal() {
        try {
            DynamicDatasourceContextHolder.set(DatasourceType.GLOBAL);
            List<User> users = service.findAll();
            return users.toString();
        } finally {
            DynamicDatasourceContextHolder.remove();
        }
    }

}
