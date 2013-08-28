package com.otkmnb.boot.example.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import com.otkmnb.boot.example.service.HelloWorldService;

@Path("/hello")
@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
@Consumes(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    
    @Autowired
    private HelloWorldService service;
    
    @Path("/{id}")
    @GET
    public String findBy(@PathParam("id") int id) {
        service.findBy(id);
        return "success";
    }

}
