package com.bitaron.archtype.resources;


import com.bitaron.archtype.customResponses.CustomResponse;
import com.bitaron.archtype.customResponses.CustomResponseCode;
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
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse simpleHttpWithoutSecurity(){
        return new CustomResponse(CustomResponseCode.SUCCESS, "No security Mr.!");
    }

    @GET
    @Path("basicAuth")
    @Produces(MediaType.APPLICATION_JSON)
    @BasicAuth
    public CustomResponse simpleHttpWithBasicAuth(){
        return new CustomResponse(CustomResponseCode.SUCCESS, "Basic Authentication is here!");
    }

    @GET
    @Path("getJwtToken")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomResponse getJwtToken(){
        return new CustomResponse(CustomResponseCode.SUCCESS, JwtHelper.createToken());
    }

    @GET
    @Path("jwtToken")
    @Produces(MediaType.APPLICATION_JSON)
    @JwtAuth
    public CustomResponse simpleHttpWithJWT(@Context SecurityContext securityContext){
        return new CustomResponse(CustomResponseCode.SUCCESS, securityContext.getUserPrincipal().getName());
    }
}
