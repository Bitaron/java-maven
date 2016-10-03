package com.bitaron.archtype.security.filters;


import com.bitaron.archtype.security.annotations.JwtAuth;
import com.bitaron.archtype.security.jwt.JwtHelper;
import io.jsonwebtoken.Claims;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;

@JwtAuth
@Provider
@Priority(Priorities.AUTHORIZATION)
public class JwtFilter implements ContainerRequestFilter {

    private static final String HEADER_KEY = "Authorization";
    private static final String HEADER_PREFIX = "Bearer ";
    //JWT token: eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxIn0.gE5WOBhKP21aGIinBYuEkgXYf7d2Pa1BZEwjlI_TRUo

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

        List<String> authHeader = containerRequestContext.getHeaders().get(HEADER_KEY);
        if (authHeader != null && authHeader.size() > 0) {
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(HEADER_PREFIX, "");
            Claims claim = JwtHelper.parseJWT(authToken);
            if(claim==null){
                Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Invalid JWT token.").build();
                containerRequestContext.abortWith(unauthorizedStatus);
            }else{
                return;
            }
        }else{
            Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                    .entity("No `Bearer` Token found in `Authorization` header.").build();

            containerRequestContext.abortWith(unauthorizedStatus);
        }
    }
}