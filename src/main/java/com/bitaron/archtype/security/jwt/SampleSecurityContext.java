package com.bitaron.archtype.security.jwt;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;


public class SampleSecurityContext implements SecurityContext {
    Principal principal;

    public SampleSecurityContext(Principal principal) {
        this.principal = principal;
    }

    @Override
    public Principal getUserPrincipal() {
        return principal;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
