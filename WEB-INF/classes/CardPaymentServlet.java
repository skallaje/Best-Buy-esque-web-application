import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class CardPaymentServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
        CreateDBEntities en = new CreateDBEntities();
        for(ItemOrder io: ItemsList.it){
            en.insertSoldItems(io.getItem().getItemID(), io.getItem().getPID(), io.getItem().getShortDescription(), String.valueOf(io.getItem().getCost()), io.getItem().getZip(), io.getItem().getState(), io.getItem().getCity());
        }        
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		FinalOrder fo = new FinalOrder(Credentials.getUser(), date, request.getParameter("cnum"));		
        en.insertOrder(fo.getOrderConfirmationID(),fo.getUserName(),fo.getOrderDate(),fo.getDeliveryDate(),fo.getCreditCardNumber(),fo.getOrderMessage());
		String address = request.getParameter("add1") + " "+request.getParameter("add2") + " "+request.getParameter("city")+" "+request.getParameter("state") +" "+request.getParameter("zip");
		pw.println("<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title>");
		pw.println("<link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1>");
		pw.println("<h2>Amazing Products!</h2></header><nav><ul><li class='start selected'><a href='/A4/LoggedInUserServlet?p=Customer'>Home</a></li><li><a href='#'>Products</a></li>");
        pw.println("<li class='end'><a href='#'>Contact</a><li><a>Hello "+Credentials.getUser()+" </a></li><li><a href='/A4/ViewOrderHistoryServlet'>Order History</a></li><li><a href='/A4/TrendingServlet'>Trending</a></li><li><form method='get' class='searchform' action='#'><input type='TEXT' size='15' name='srchfield' value='Search...'></input><input class = 'button' type='submit' name='srchbox' value='GO' /></form></li><li><a href='/A4/LogoutServlet'>LOG OUT</a></li></ul></nav>");
		pw.println("<div id='body'><section id='content'><div id = 'details'>");
		pw.println("<h3>Your order has been successfully placed.</h3>");
		pw.println("<h4>Here are the details.</h4>");
		pw.println("<h5>Confirmation ID: "+fo.getOrderConfirmationID()+"</h5>");
		pw.println("<h4>Order will be delivered to, </h4>");
		pw.println("<h5>Name: "+fo.getUserName()+"</h5>");
		pw.println("<h5>Address: "+address+"</h5>");
		pw.println("<h5>Ordered on: "+fo.getOrderDate()+"</h5>");
		pw.println("<h5>Message: "+fo.getOrderMessage()+"</h5>");
		pw.println("</div></section><aside class='sidebar'><ul><li><h4>Products</h4><ul><li><a href='/A4/RetrieveProductInfoServlet?category=TV'>TVs</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Laptop'>Laptops</a></li>");
        pw.println("<li><a href='/A4/RetrieveProductInfoServlet?category=SmartPhone'>SmartPhones</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Tablet'>Tablets</a></li></ul></li></ul></aside>");
    	pw.println("<div class='clear'></div></div><footer><div class='footer-content'><h5>Prices and offers are subject to change. © 2016 Best Deals. All rights reserved. BEST DEALS, the BEST DEAL logo, the tag design, MY BEST DEAL, and BESTDEALS.COM are trademarks of Best Deals and its affiliated companies.</h5>");
        pw.println("</div></footer></div></body></html>");	
		ItemsList.clear();
		ItemsList.finalCost=0;
    } 
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    } 
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        processRequest(request, response);
    }
}