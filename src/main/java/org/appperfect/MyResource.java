package org.appperfect;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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
	private String order,num,next,prev,first,last;
	private static int offset;
	private static boolean initialRun;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getIt(@QueryParam("num") String num,@QueryParam("order") String order,@QueryParam("next") String next,@QueryParam("prev") String prev,@QueryParam("first") String first,@QueryParam("last") String last) {
    	JSONArray jsonResult;
    	if(initialRun==false){
    		initialRun=true;
    	}
    	else{
    		if(num==null||num.isEmpty()){
    			this.num="5";
    		}
    		else{
    			this.num=num;	
    		}
    		if(order==null||order.isEmpty()){
    			this.order="";
    		}
    		else{
    			this.order=order;
    		}
    		if(next==null||next.isEmpty()){
    			this.next="";
    		}
    		else{
    			this.next=next;
    		}
    		if(first==null||first.isEmpty()){
    			this.first="";
    		}
    		else{
    			this.first=first;
    		}
    		if(last==null||last.isEmpty()){
    			this.last="";
    		}
    		else{
    			this.last=last;
    		}
    		if(prev==null||prev.isEmpty()){
    			this.prev="";
    		}
    		else{
    			this.prev=prev;
    		}
    	}
    	jsonResult=ResultToJson.getJson();
    	System.out.println(jsonResult);
        return Response.status(200).entity(jsonResult.toString()).build();
    }
}
