package com.bitaron.archtype.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        try{
            int i = 1/0;
        }catch (Exception e){
            System.out.print("\n\nPringing exceptions\n");
            e.printStackTrace();
        }

        return "Seed app for jersey-security!";
    }

    @GET
    @Path("error")
    @Produces(MediaType.TEXT_PLAIN)
    public String getError() {
        int i = 1 / 0;
        return null;
    }
}
