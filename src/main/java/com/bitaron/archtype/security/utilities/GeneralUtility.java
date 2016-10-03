package com.bitaron.archtype.security.utilities;


import com.bitaron.archtype.customResponses.CustomResponse;
import com.bitaron.archtype.customResponses.CustomResponseCode;

import javax.ws.rs.core.Response;

public class GeneralUtility {

    public static Response createUnauthorizedResponse(String message) {
        Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                .entity(new CustomResponse(CustomResponseCode.UNAUTHORIZED,message)).build();
        return unauthorizedStatus;
    }
}
