import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.ResultSet;

public class ViewOrderHistoryServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException, java.sql.SQLException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
        AppUtilities au = new AppUtilities();
		pw.println("<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title>");
		pw.println("<link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1>");
		pw.println("<h2>Amazing Products!</h2></header><nav><ul><li class='start selected'><a href='/A4/LoggedInUserServlet?p=Customer'>Home</a></li><li><a href='#'>Products</a></li>");
        pw.println("<li class='end'><a href='#'>Contact</a><li><a>Hello "+Credentials.getUser()+" </a></li><li><a href='/A4/ViewOrderHistoryServlet'>Order History</a></li><li><a href='/A4/TrendingServlet'>Trending</a></li><li><form method='get' class='searchform' action='#'><input type='TEXT' size='15' name='srchfield' value='Search...'></input><input class = 'button' type='submit' name='srchbox' value='GO' /></form></li><li><a href='/A4/LogoutServlet'>LOG OUT</a></li></ul></nav>");
		pw.println("<div id='body'><section id='content'><div id = 'product'><div id = 'image'>");
		if(firstPair==null){
			pw.println("<h5>ENTER YOUR ORDER ID:</h5>");
			pw.println("<li><form method='get' class='searchform' action='/A4/ViewOrderHistoryServlet?p=check' ><input type='TEXT' size='15' name='oidsrchfield'></input><input class = 'button' type='submit' name='srchbox' value='GO' /></form></li>");
		}else{
			int flag1 =0, flag2 = 0;
            CreateDBEntities en = new CreateDBEntities();
            ResultSet rs = en.checkOrderExists(Credentials.getUser(),"N/A");
                while(rs.next()){
                        System.out.println("Here 22");
                        if(firstPair.split("=")[1].equals("cancel")){
                            en.deleteOrder(OrderHistory.oid);
                            flag2=1;
                            System.out.println(OrderHistory.oid);
                            pw.println("<h4>Your order has been cancelled.</h4>");
                            break;
                        }else{
                            if(rs.getString(1).trim().equals(request.getParameter("oidsrchfield").trim())){
                                OrderHistory.oid=rs.getString(1).trim();
                                flag1 = 1;
                                pw.println("<h4> UserID: "+rs.getString(2)+"</h4><h4> OrderID: "+rs.getString(1)+"</h4>");
                                pw.println("<h4> Order Date: "+rs.getString(3)+"</h4>");
                                pw.println("<h4> Order Message: "+rs.getString(6)+"</h4>");
                                if(au.returnDaysGap(rs.getString(3)) <= 14){
                                    pw.println("<a href='/A4/ViewOrderHistoryServlet?p=cancel' class='button' name='cancelorder'>CANCEL ORDER</a>");
                                }
                            }
                            
                        }
                }
					
				if(flag1==0 && flag2==0){
					pw.println("<h4>No such Order ID exists!</h4>");
				}
		}
		pw.println("</section><aside class='sidebar'><ul><li><h4>Products</h4><ul><li><a href='/A4/RetrieveProductInfoServlet?category=TV'>TVs</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Laptop'>Laptops</a></li>");
        pw.println("<li><a href='/A4/RetrieveProductInfoServlet?category=SmartPhone'>SmartPhones</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Tablet'>Tablets</a></li></ul></li></ul></aside>");
    	pw.println("<div class='clear'></div></div><footer><div class='footer-content'><h5>Prices and offers are subject to change. © 2016 Best Deals. All rights reserved. BEST DEALS, the BEST DEAL logo, the tag design, MY BEST DEAL, and BESTDEALS.COM are trademarks of Best Deals and its affiliated companies.</h5>");
        pw.println("</div></footer></div></body></html>");
		
    } 
  
    protected void showPage(HttpServletResponse response, String s)
    throws ServletException, java.io.IOException, java.sql.SQLException {
       response.setContentType("text/html");
	   PrintWriter pw = response.getWriter();
	   pw.println(s);	   
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try{
            processRequest(request, response);
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
    } 
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try{
            processRequest(request, response);
        }catch(java.sql.SQLException e){
            e.printStackTrace();
        }
    }
}