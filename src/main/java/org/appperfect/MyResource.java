package org.appperfect;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
	private String order,num,next,prev,first,last,param;
	private static int offset;
	private static boolean reachLast,reachFirst;
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public Response getIt(@FormParam("num") String num,@FormParam("order") String order,@FormParam("next") String next,@FormParam("prev") String prev,@FormParam("first") String first,@FormParam("last") String last,@FormParam("param") String param,@FormParam("reset") String reset) {
    	String result="";
    	int numr=5;
    	JSONArray jsonArray = null;
    	String query=AccessDB.getQuery();
    	int size=AccessDB.getRecordSize();
    		if(reset!=null && !reset.isEmpty()){
    			System.out.println("reset clicked ");
    			offset=0;
    		}
    		if(param==null||param.isEmpty()){
    			this.param="";
    		}
    		else{
    			this.param=param;
    			query=query+" ORDER BY "+this.param;
    		}
    		if(order==null||order.isEmpty()){
    			this.order="ASC";
    		}
    		else{
    			this.order=order;
    			query=query+" "+this.order;
    		}
    		if(num==null||num.isEmpty()){
    			this.num="5";
    			query=query+" LIMIT "+this.num;
    		}
    		else{
    			this.num=num;	
    			numr=Integer.parseInt(num);
    			query=query+" LIMIT "+this.num;
    		}
    		
    		if(next==null||next.isEmpty()){
    			this.next="";
    		}
    		else{
    			this.next=next;
    			if(reachLast==false){
    			offset=offset+Integer.parseInt(this.num);
    			if(offset>=size){
    				reachLast=true;
    				reachFirst=false;
    			}
    			}
    				query=query+" OFFSET "+offset;
    				System.out.println("offset in next is "+offset);
    			}
    		if(prev==null||prev.isEmpty()){
    			this.prev="";
    		}
    		else{
    			this.prev=prev;
    			System.out.println("previous active");
    			if(reachFirst==false){
    			offset=offset-Integer.parseInt(this.num);
    			if(offset<=0)
    			{
    				offset=0;
    				reachFirst=true;
    			}
    			
    			}
    			System.out.println("offset in previous is "+offset);
    			query=query+" OFFSET "+offset;
    		}
    		if(first==null||first.isEmpty()){
    			this.first="";
    		}
    		else{
    			this.first=first;
    			offset=0;
    			query=query+" OFFSET "+offset;
    			reachFirst=true;
    			reachLast=false;
    			System.out.println("offset in first is "+offset);
    		}
    		if(last==null||last.isEmpty()){
    			this.last="";
    		}
    		else{
    			this.last=last;
    			offset=size-size%numr;
    			query=query+" OFFSET "+offset;
    			reachLast=true;
    			reachFirst=false;
    			System.out.println("offset in last is "+offset);
    		}
    		if(offset>0 && offset<size)
    		{
    		reachLast=false;
    		reachFirst=false;
    		}
    		System.out.println("the reached first has value "+reachFirst);
    		System.out.println("the reached last has value "+reachLast);
    	try {
    		System.out.println(query);
    		AccessDB.execute(query);
			ResultToJson.convert(AccessDB.getRs());
			JSONObject obj=new JSONObject();
			jsonArray=ResultToJson.getJson();
			if(reachLast==true)
			{
				//reachFirst=false;
				obj.put("end","Reachedlast");
			}
			else if(reachFirst==true)
			{
				//reachLast=false;
				obj.put("end", "Reachedfirst");
			}
			else{
				obj.put("end","somewhere");
			}
			jsonArray.put(obj);
		} catch (SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	if(jsonArray!=null)
    	result=jsonArray.toString();
    	if(result==null||result.isEmpty()){
    		result="";
    		System.out.println("result is empty or null");
    	}
    	System.out.println(result);
        return Response.status(200).entity(result).build();
    }
    public static void reset(){
    	offset=0;
    	reachFirst=false;
    	reachLast=false;
    }
}
