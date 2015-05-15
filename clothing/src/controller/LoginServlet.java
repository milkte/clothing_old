package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;






import com.dao.AuthDAO;
import com.model.User;
import javax.servlet.jsp.PageContext;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loggedIn = "false";
        System.out.println("in login");
        // read username and password from request
        String username = req.getParameter("username");
        String password = req.getParameter("password");
	if(username == null){
		username = "";
	}
	if(password == null){
		password = "";
	}
        String msg = "";
        String url = "/index.jsp";
        // validate username and password
        if (username.equals("")) {
            msg = "Please Enter Username";
            url = "/login.jsp";
        } else if (password.equals("")) {
            msg = "Please Enter Password";
            url = "/login.jsp";
        } else {
        	
            // check username and password with database
            if (AuthDAO.checkUserPass(username, password)==0) {
            	// set error message for invalid password
                msg = "Username/Password does Not Match";
                url = "/login.jsp";
            } else {
            	System.out.println("Login");
            	User user=AuthDAO.getUserByUserNamePassword(username, password);
            	System.out.println("loggedin" + user.getRole());
            	// create session for user
                HttpSession session = req.getSession(true);
                loggedIn = "true";
                // set value in session
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                session.setAttribute("loggedIn", loggedIn);
                System.out.println("Setting userid: " + user.getUserId());
                session.setAttribute("userid", user.getUserId());
                System.out.println("user.getUserId() " + user.getUserId());
                session.setAttribute("role", user.getRole());
                
                url = "/index.jsp";
                msg= user.getFirstName()+ "  " + user.getLastName()+" has loged in";
                 req.setAttribute("msg",msg);
       req.setAttribute("loggedIn",loggedIn);
       req.setAttribute("url", url);
//        resp.sendRedirect(url);
        System.out.println(url);
        System.out.println(loggedIn);
                return;
            }
        }
        // set messsage and url in request
       req.setAttribute("msg",msg);
       req.setAttribute("loggedIn",loggedIn);
       req.setAttribute("url", url);
       RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
       dispatcher.forward(req, resp);  
//        try {
//        	
//        	// close DB connection
////            AuthDAO.DB_Close();
//        } catch (Throwable ex) {
//            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
              
    }

}
