package com.bitaron.archtype.resources;


import com.bitaron.archtype.security.annotations.BasicAuth;
import com.bitaron.archtype.security.annotations.JwtAuth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("security")
public class SecurityResource {

    @GET
    @Path("simpleHTTP")
    @Produces(MediaType.TEXT_PLAIN)
    public String simpleHttpWithoutSecurity(){
        return "No security Mr.!";
    }

    @GET
    @Path("basicAuth")
    @Produces(MediaType.TEXT_PLAIN)
    @BasicAuth
    public String simpleHttpWithBasicAuth(){
        return "Basic Authentication is here!";
    }

    @GET
    @Path("jwtToken")
    @Produces(MediaType.TEXT_PLAIN)
    @JwtAuth
    public String simpleHttpWithJWT(){
        return "JWT Authentication is here!";
    }
}
