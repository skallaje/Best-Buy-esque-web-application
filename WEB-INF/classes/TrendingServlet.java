import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class TrendingServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException, java.sql.SQLException {
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();        
		pw.println("<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title>");
		pw.println("<link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1>");
		pw.println("<h2>Amazing Products!</h2></header><nav><ul><li class='start selected'><a href='/A4/LoggedInUserServlet?p=Customer'>Home</a></li><li><a href='#'>Products</a></li>");
        pw.println("<li class='end'><a href='#'>Contact</a><li><a>Hello "+Credentials.getUser()+" </a></li><li><a href='/A4/ViewOrderHistoryServlet'>Order History</a></li><li><a href='/A4/TrendingServlet'>Trending</a></li><li><form method='get' class='searchform' action='#'><input type='TEXT' size='15' name='srchfield' value='Search...'></input><input class = 'button' type='submit' name='srchbox' value='GO' /></form></li><li><a href='/A4/LogoutServlet'>LOG OUT</a></li></ul></nav>");
		pw.println("<div id='body'><section id='content'><div id = 'details'>");
        AppUtilities au = new AppUtilities();
        au.mostLikedProducts(pw);
        au.zipMaxSoldProducts("zip", pw);
        au.zipMaxSoldProducts("maxsold", pw);
		pw.println("</div></section><aside class='sidebar'><ul><li><h4>Products</h4><ul><li><a href='/A4/RetrieveProductInfoServlet?category=TV'>TVs</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Laptop'>Laptops</a></li>");
        pw.println("<li><a href='/A4/RetrieveProductInfoServlet?category=SmartPhone'>SmartPhones</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Tablet'>Tablets</a></li></ul></li></ul></aside>");
    	pw.println("<div class='clear'></div></div><footer><div class='footer-content'><h5>Prices and offers are subject to change. © 2016 Best Deals. All rights reserved. BEST DEALS, the BEST DEAL logo, the tag design, MY BEST DEAL, and BESTDEALS.COM are trademarks of Best Deals and its affiliated companies.</h5>");
        pw.println("</div></footer></div></body></html>");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try{
            processRequest(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    } 
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try{
            processRequest(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}