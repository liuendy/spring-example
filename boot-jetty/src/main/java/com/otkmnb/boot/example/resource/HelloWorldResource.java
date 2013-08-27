package com.otkmnb.boot.example.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.otkmnb.boot.example.service.HelloWorldService;

@Path("/hello")
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    
    @Autowired
    private HelloWorldService service;
    
    @Path("/{id}")
    @DELETE
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

}
