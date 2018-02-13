package org.appperfect;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	String order,num,next,prev,first,last;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt() {
    	try {
			ResultToJson.convert(AccessDB.getRs());
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONArray jsonResult=ResultToJson.getJson();
    	System.out.println(jsonResult);
        return Response.status(200).entity(jsonResult.toString()).build();
    }
}
