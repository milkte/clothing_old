/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.dao.ItemDAOImpl;
import com.dao.OrderDao;
import com.dao.OrderItemDao;
import com.google.gson.Gson;
import com.model.Item;
import com.model.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shruti
 */
public class OrderServlet extends HttpServlet {

    OrderDao orderDao = new OrderDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String url = "/payment.jsp";
    	 String msg ="<h5 style=\"color:green\">Item Added successfully.</h5>";
        System.out.println("OrderServlet");
        String get = request.getParameter("getOrderForSeller");
        String edit = request.getParameter("editOrder");
        if (get != null) {
            System.out.println("in get");
            String seller = request.getParameter("seller");
            System.out.println("seller: " + seller);
            List<Order> orderBySeller = orderDao.getOrderBySeller(seller);
            System.out.println("orderBySeller" + orderBySeller);
              String json = new Gson().toJson(orderBySeller);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            return;
        }
        if (edit != null) {
           
        }
        response.setContentType("text/html;charset=UTF-8");
        try  {
        
            ItemDAOImpl itemDAO = new ItemDAOImpl();
            Integer userid = (Integer) request.getSession().getAttribute("userid");
            int noOfitems = Integer.parseInt(request.getParameter("noOfitems"));
            String payment = request.getParameter("payment");
            Set<Integer> sellerSet = new HashSet<>();
            String id = request.getParameter("id_0");
            int qty = Integer.parseInt(request.getParameter("qty_0"));
            String[] split = id.split("-");
            Item item = new ItemDAOImpl().getItemById(Integer.parseInt(split[0]));
            int sellerid=-1;
            if(null!=item.getSeller()){
             sellerid = item.getSeller().getUser().getUserId();
            	sellerSet.add(sellerid);
            }
            String addType = request.getParameter("add");
            String address="";
            if(addType.equals("0")){
                address += request.getParameter("street");
                address +=  request.getParameter("city");
                address +=  request.getParameter("state");
                address +=  request.getParameter("pincode");
            }
//            String total = request.getParameter("total");
            int orderid = orderDao.addInitialOrder(0, sellerid, address, userid, payment, "0");
              OrderItemDao.addOrderItem(split[0], split[1], split[2],sellerid, orderid, qty, item.getPrice());
             itemDAO.reduceSttock(split[0], split[1], split[2], qty);
//            OrderItemDao.addOrderItem(split[0], split[1], split[2], orderid, qty, item.getPrice());
            for (int i = 1; i < noOfitems; i++) {
                id = request.getParameter("id_" + i);
                qty = Integer.parseInt(request.getParameter("qty_" + i));
                split = id.split("-");
                item = new ItemDAOImpl().getItemById(Integer.parseInt(split[0]));
                sellerid = item.getSeller().getUser().getUserId();
                if (sellerSet.add(sellerid)) {
                    orderDao.addInitialOrder(orderid, sellerid, "1", userid, payment, "0");
                }
                 OrderItemDao.addOrderItem(split[0], split[1], split[2],sellerid, orderid, qty, item.getPrice());
                  itemDAO.reduceSttock(split[0], split[1], split[2], qty);
                System.out.println("splitlength" + split.length);
            }
        }catch(Exception ex){
        }
        request.setAttribute("msg", msg); 	
        RequestDispatcher dispatcher = getServletContext()
                .getRequestDispatcher(url);
        dispatcher.include(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
