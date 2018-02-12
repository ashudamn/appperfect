<html>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
    for more information on Jersey!
    <!--<a href="webapi/handleuser">just go away</a>-->
    <form action="webapi/handleuser" method="get">
    Driver:<input type="text" name="driver" id="driver" required><br>
    URL:<input type="text" name="url" id="url" required><br>
    Username:<input type="text" name="username" id="username" required><br>
    Password:<input type="password" name="password" id="password" required><br>
    Query:<input type="text" name="query" id="query" required><br>
    <input type="submit">
    </form>
</body>
</html>
