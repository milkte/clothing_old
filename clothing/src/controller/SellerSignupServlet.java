package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AuthDAO;
import com.model.User;

/**
 * Servlet implementation class LoginServlet
 */
public class SellerSignupServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            java.io.IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String msg = "";
        String url = "/signup.jsp";
        // read user info from request
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String cpassword = req.getParameter("conpassword");
        
        String company = req.getParameter("company");
        String email = req.getParameter("email");
        String phno = req.getParameter("phno");
        String city = req.getParameter("city");
        String state = req.getParameter("state");
        String country = req.getParameter("country");
        String pincode = req.getParameter("pincode");
        boolean validationPassed = true;
        // validate fields
        if (username.equals("")) {
            msg = "<h5 style=\"color:red\">Username not filled in</h5>";
            url = "/signup.jsp";
            validationPassed = false;
        } else if (password.equals("")) {
            msg = "<h5 style=\"color:red\">Password not filled in</h5>";
            url = "/signup.jsp";
            validationPassed = false;
        } else if (company.equals("")) {
            msg = "<h5 style=\"color:red\">Last Name not filled in</h5>";
            url = "/signup.jsp";
            validationPassed = false;
        } else if (!password.equals(cpassword)) {
            msg = "<h5 style=\"color:red\">Password Does Not Match</h5>";
            url = "/signup.jsp";
            validationPassed = false;
        }

		// validate user is available or not
          if (req.getParameter("validate") == null) {
            if (validationPassed) {
                AuthDAO.enterUserName(username, password,company,"",email,phno,city,state,country,Long.parseLong(pincode),AuthDAO.SELLER_ROLE);
                User user = AuthDAO.getUserByUserName(username);
                if (user != null) {
                    msg = "<h5 style=\"color:green\">User created successfully</h5>";
                    url = "/signup.jsp";
                }
            }
        } else {
            boolean userNameAvailable = AuthDAO.isUserNameAvailable(username);
            System.out.println("userNameAvailable " + userNameAvailable);
            if (userNameAvailable) {
                msg = "<h5 style=\"color:green\">Username is available.</h5>";
                url = "/signup.jsp";

            } else {
                username="";
                msg = "<h5 style=\"color:red\">Username is not available.</h5>";
            }
        }
      // set message to request
             req.setAttribute("userName", username);
     
        req.setAttribute("msg", msg);
        req.setAttribute("url", url);
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.include(req, resp);
        try {
            // close DB connection
            AuthDAO.DB_Close();
        } catch (Throwable ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

}
