package com.bitaron.archtype.resources;


import com.bitaron.archtype.responses.Response;
import com.bitaron.archtype.responses.ResponseCode;
import com.bitaron.archtype.security.annotations.BasicAuth;
import com.bitaron.archtype.security.annotations.JwtAuth;
import com.bitaron.archtype.security.jwt.JwtHelper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

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
    @Path("getJwtToken")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJwtToken(){
        return new Response(ResponseCode.SUCCESS, JwtHelper.createToken());
    }

    @GET
    @Path("jwtToken")
    @Produces(MediaType.APPLICATION_JSON)
    @JwtAuth
    public Response simpleHttpWithJWT(@Context SecurityContext securityContext){
        return new Response(ResponseCode.SUCCESS, securityContext.getUserPrincipal().getName());
    }
}
