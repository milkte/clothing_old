<%@ page language="java" 
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
         %>

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
                loggedIn = "";
            }
        %>
        

        <%
            if (!loggedIn.equalsIgnoreCase("true")) {
                %>
                 <div class="col-md-10 col-md-offset-1 col-lg-10 col-lg-offset-1">
                     <h1 style="color:darkgray">Join us as Seller</h1></hr>
        
        <p><%= msg%></p>
        <br>
                     <form action='SellerSignupServlet' method='post'>
              <table class="table table-hover"><tr><td><lable class="control-label">Username : </lable></td>
              <td><input type='text' class="form-control"  name='username'  maxlength='45' value='<%=username%>'/></td>
              <td> <input class="btn btn-toolbar" type='submit' value='validate' name='validate'/></td></tr>
                <tr><td><lable class="control-label">Company Name : </lable></td>
                <td><input type='text' class="form-control" name='company'  maxlength='45'/></td> </tr>
                <tr><td><lable class="control-label">Password : </lable></td>
                <td><input type='password'  class="form-control" name='password'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">Confirm Password : </lable></td>
                <td><input type='password'  class="form-control" name='conpassword'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">Email address : </lable></td><td><input type='text' class="form-control" name='email'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">Phone number : </lable></td><td><input type='text' class="form-control" name='phno'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">City : </lable></td><td><input type='text' class="form-control" name='city'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">State : </lable></td><td><input type='text' class="form-control" name='state'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">Country : </lable></td><td><input type='text' class="form-control" name='country'  maxlength='45'/></td></tr>
                <tr><td><lable class="control-label">Pincode : </lable></td><td><input type='number'  class="form-control" name='pincode'  maxlength='45'/></td></tr>
                <tr><td><input class="btn btn-toolbar" type='submit' value='Sign Up' /></td></tr></table>
        </form>
                 </div>
        <%  } else {%>
        <p> you are currently logged in</p>
        <form action='Logout' mthod='post'>
            <input type='submit' value='Logout' name='Logout'/>
        </form>
            <%}
        %>			
    </body>
</html>
