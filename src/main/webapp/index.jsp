<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <!--<a href="webapi/handleuser">just go away</a>-->
    <form action="webapi/handleuser" method="post">
    Driver:<input type="text" name="driver" id="driver" value="com.mysql.jdbc.Driver" required><br>
    URL:<input type="text" name="url" id="url" value="jdbc:mysql://localhost:3306/northwind" required><br>
    Username:<input type="text" name="username" id="username" value="root" required><br>
    Password:<input type="password" name="password" id="password" value="12345678" required><br>
    Query:<input type="text" name="query" id="query" required><br>
    <input type="submit">
    </form>
</body>
</html>
