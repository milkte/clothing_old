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
import com.dao.ItemDAOImpl;
import com.google.gson.Gson;
import com.model.Category;
import com.model.Item;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 */
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;
    ItemDAOImpl itemDAO = new ItemDAOImpl();

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException,
            java.io.IOException {
        String msg = "";
        String url = "/search.jsp";
        // read user info from request
        String categoryId = request.getParameter("category");
        String itemId = request.getParameter("item");
        System.out.println("beforeitems");
        List<Item> items = (List<Item>) request.getAttribute("items");
        System.out.println("items"+items);
        Item item = null;
        if (categoryId == null) {

        } else {
            items = itemDAO.getItemsByCategory(Integer.parseInt(categoryId));
        }
        if (items != null) {
        } else {
            msg = "No sub categories found";
        }
        
        if (itemId != null && !itemId.isEmpty()) {
            item = itemDAO.getItemById(Integer.parseInt(itemId));
            String json = new Gson().toJson(item);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }
        // set message to request
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        request.setAttribute("items", items);
        request.setAttribute("item", item);
        
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.forward(request, response);
        try {
            // close DB connection
            AuthDAO.DB_Close();
        } catch (Throwable ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(request, resp);
    }

}
