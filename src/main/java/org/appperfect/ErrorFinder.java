package org.appperfect;

public class ErrorFinder {
	private static String errorMsg;
	public ErrorFinder()
	{
		
	}
	public static void setError(String errorMsg)
	{
		ErrorFinder.errorMsg=errorMsg;
	}
	public static String getError()
	{
		return ErrorFinder.errorMsg;
	}
	public static void validateRequired(String driver,String url,String username,String password,String query) throws Exception
	{
		if(driver==null)
		{
			System.out.println("driver is null");
			throw new Exception("driver is null");
		}
		if(url==null){
			System.out.println("url is null");
			throw new Exception("url is null");
		}
		if(username==null){
			System.out.println("username is null");
			throw new Exception("username is null");
		}
		if(password==null){
			System.out.println("password is null");
			throw new Exception("password is null");
		}
		if(query==null){
			System.out.println("query is null");
			throw new Exception("query is null");
		}
		
		if(driver.isEmpty())
		{
			System.out.println("driver name is required");
			throw new Exception("driver name is required");
		}
		if(url.isEmpty())
		{
			System.out.println("url is required");
			throw new Exception("url is required");
		}
		if(username.isEmpty())
		{
			System.out.println("username is required");
			throw new Exception("username is required");
		}
		if(password.isEmpty())
		{
			System.out.println("password is required");
			throw new Exception("password is required");
		}
		if(query.isEmpty())
		{
			System.out.println("query is required");
			throw new Exception("query is required");
		}
	}
public static void findError(Exception e) {
	setError(e.getMessage());
	e.printStackTrace();
	

}
}
