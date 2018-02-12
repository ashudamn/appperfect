package org.appperfect;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
@Path("/handleuser")
public class HandleUser {
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getData(@QueryParam("driver") String driver,@QueryParam("url") String url,@QueryParam("username") String username,@QueryParam("query") String query){
		/*@FormParam("driver") String driver;
		@FormParam("url") String url;
		@FormParam("username") String username;
		@FormParam("query") String query;*/
		String responce="Driver is "+driver+" URL is "+url+" "+" username is "+username+" query is "+query;
		return Response.status(200).entity(responce).build();
	}
}
