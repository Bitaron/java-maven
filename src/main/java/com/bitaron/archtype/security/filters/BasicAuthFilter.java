package com.bitaron.archtype.security.filters;


import com.bitaron.archtype.security.annotations.BasicAuth;
import com.bitaron.archtype.security.utilities.GeneralUtility;
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
        boolean validAuthHeader = authHeader != null && authHeader.size() > 0;

        if (validAuthHeader) {

            try {
                StringTokenizer tokenizer = extractAndDecodeBasicAuthToker(authHeader.get(0));
                String userName = tokenizer.nextToken();
                String passWord = tokenizer.nextToken();

                if (userName.equals(USER_NAME) && passWord.equals(PASSWORD)) {
                    return;
                } else {
                    Response unauthorizedStatus = GeneralUtility.createUnauthorizedResponse("Username and Password doesn't match");
                    containerRequestContext.abortWith(unauthorizedStatus);
                }
            }catch (Exception e) {
                Response unauthorizedStatus = GeneralUtility.createUnauthorizedResponse("Username and Password doesn't match");
                containerRequestContext.abortWith(unauthorizedStatus);
            }

        }else {
            Response unauthorizedStatus = GeneralUtility.createUnauthorizedResponse("No `Basic` token found in `Authorizaton` header.");
            containerRequestContext.abortWith(unauthorizedStatus);
        }
    }

    private StringTokenizer extractAndDecodeBasicAuthToker(String basicAuthToken) {
        basicAuthToken = basicAuthToken.replaceFirst(HEADER_PREFIX, "");
        String decodedString = Base64.decodeAsString(basicAuthToken);
        StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
       return tokenizer;
    }



}
