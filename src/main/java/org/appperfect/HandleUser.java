package org.appperfect;

import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
@Path("/handleuser")
public class HandleUser {
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response getData(@FormParam("driver") String driver,@FormParam("url") String url,@FormParam("username") String username,@FormParam("password") String password,@FormParam("query") String query){
		//String respMessage="hi :)";
		Response response = null;
		try {
			ErrorFinder.validateRequired(driver, url, username, password, query);
			AccessDB.connect2DB(driver, url, username, password);
			AccessDB.execute(query);
			AccessDB.setQuery(query);
			ResultToJson.convert(AccessDB.getRs());
			AccessDB.setRecordSize(ResultToJson.getJson().length());
			System.out.println(AccessDB.getQuery());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//respMessage=e.getMessage();
			ErrorFinder.findError(e);
			e.printStackTrace();
			MyResource.reset();
			try {
				response= Response.seeOther(new URI("http://localhost:8080/appperfect/Error.jsp")).build();
				
			} catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return response;
		}
		
		try {
			response = Response.seeOther(new URI("http://localhost:8080/appperfect/second.html")).build();
			MyResource.reset();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			System.out.println("problem in generating response");
			e.printStackTrace();
		}
		return response;
	}
}
