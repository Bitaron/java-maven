package com.bitaron.archtype.security.filters;


import com.bitaron.archtype.security.annotations.BasicAuth;
import org.glassfish.jersey.internal.util.Base64;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@BasicAuth
@Provider
@Priority(Priorities.AUTHENTICATION)
public class BasicAuthFilter implements ContainerRequestFilter {

    private static final String HEADER_KEY = "Authorization";
    private static final String HEADER_PREFIX = "Basic ";

    private static final String USER_NAME = "userName";
    private static final String PASSWORD = "password";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        List<String> authHeader = containerRequestContext.getHeaders().get(HEADER_KEY);
        if (authHeader != null && authHeader.size() > 0) {
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(HEADER_PREFIX, "");
            String decodedString = Base64.decodeAsString(authToken);
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String userName = tokenizer.nextToken();
            String passWord = tokenizer.nextToken();
            if (userName.equals(USER_NAME) && passWord.equals(PASSWORD)) {
                return;
            }

        }

        Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                .entity("Username and Password doesn't match").build();

        containerRequestContext.abortWith(unauthorizedStatus);

    }

}
