package com.bitaron.archtype.security.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.xml.bind.DatatypeConverter;

public class JwtHelper {

    private static final String KEY = "jwtTokenTest";

    public static Claims parseJWT(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(KEY)).parseClaimsJws(jwt)
                    .getBody();
//			System.out.println("ID: " + claims.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claims;
    }
}
