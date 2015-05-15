<%@page import="com.model.User"%>
<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
        <title>Login Page</title>
        <link rel="stylesheet" href="css/bootstrap/bootstrap-theme.css"/>
        <link rel="stylesheet" href="css/bootstrap/bootstrap.css"/>

    </head>

    <body>
        <%
            String msg = (String) request.getAttribute("msg");
            if (msg == null) {
                msg = "";
            }
            String loggedIn = (String) session.getAttribute("loggedIn");
            String username = (String) request.getAttribute("userName");
            if (username == null) {
                username = "";
            }
            if (loggedIn == null) {
                out.println("You are not authorized to view this page.");
            } else {
                User user=(User)request.getAttribute("user");
        %>


        <%
            if (!loggedIn.equalsIgnoreCase("true")) {
        %>
        <div class="col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
            <h1 style="color:darkgray">User Information</h1></hr>

            <p><%= msg%></p>
            <br>
            <table class="table table-hover"><tr><td><lable class="control-label">Username : </lable></td> <td><label  class="form-control"  name='username'  value='<%=user.getUserName()%>'/></td></tr>
                    <tr><td><lable class="control-label">Company Name : </lable></td>
        <td><label  class="form-control" name='fname' value="<%=user.getFirstName()%>" /></td> </tr>
        <td><label  class="form-control" name='lname' value="<%=user.getLastName()%>" /></td> </tr>
        <tr><td><lable class="control-label">Email address : </lable></td><td><%=user.getEmail()%></td></tr>
                    <tr><td><lable class="control-label">Phone number : </lable></td><td><%=user.getPhoneNo()%></td></tr>
                    <tr><td><lable class="control-label">Street : </lable></td><td><%=user.getAddress().getStreet()%></td></tr>
                    <tr><td><lable class="control-label">City : </lable></td><td><%=user.getAddress().getCity()%></td></tr>
                    <tr><td><lable class="control-label">State : </lable></td><td><%=user.getAddress().getState()%></td></tr>
                    <tr><td><lable class="control-label">Zip code : </lable></td><td><%=user.getAddress().getZipcode()%></td></tr>
                </table>
        </div>
        <%  } else {%>
        <p> you are currently logged in</p>
        <form action='LogoutServlet' method='post'>
            <input type='submit' value='Logout' name='Logout'/>
        </form>
        <%}
            }
        %>			
    </body>
</html>
