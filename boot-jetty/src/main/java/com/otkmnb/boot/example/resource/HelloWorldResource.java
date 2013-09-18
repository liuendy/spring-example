package com.otkmnb.boot.example.resource;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.otkmnb.boot.example.service.HelloWorldService;

@Path("/hello")
public class HelloWorldResource {
    
    @Inject
    private HelloWorldService service;
    
    @Path("/{id}")
    @GET
    public String findBy(@PathParam("id") int id) {
        service.findBy(id);
        return "success";
    }

}
