import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoggedInUserServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException, SQLException {
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        String firstPair = request.getQueryString();
        ProductSerializedDataStore.loadHashMap();
		if(firstPair.split("=")[1].equals("Salesman")){
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><div align='left'><a><h3>Hello "+Credentials.getUser()+"</h3></a></div><div align='right'><a href='/A4/LogoutServlet'><h3>LOG OUT</h3></a></div><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<center><h4>Delete, Update, or Show Orders</h4><br><a href='/A4/DeleteOrdersServlet' class='button' name='deleteOrd'>DELETE</a><br><br><a href='/A4/UpdateOrdersServlet' class='button' name='updateOrd'>UPDATE</a><br><br><a href='/A4/DisplayAllOrdersServlet' class='button' name='showOrders'>DISPLAY</a><br><br><h4>Click <a href='http://localhost/A4/signup.html'>HERE</a> to create new customer accounts.</h4></center></div></div></body></html>");
		}
                    
		if(firstPair.split("=")[1].equals("StoreManager")){
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><div align='left'><a><h3>Hello "+Credentials.getUser()+"</h3></a></div><div align='right'><a href='/A4/LogoutServlet'><h3>LOG OUT</h3></a></div><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<center><h4>Add, Delete, Update, or Show Products</h4><br><a href='/A4/AddProductServlet' class='button' name='addProduct'>ADD</a><br><br><a href='/A4/DeleteProductServlet' class='button' name='deleteProd'>DELETE</a><br><br><a href='/A4/UpdateProductServlet' class='button' name='updateProd'>UPDATE</a><br><br><a href='/A4/DisplayAllProductsServlet' class='button' name='showProducts'>DISPLAY</a></center></div></div></body></html>");
		}
                    
		if(firstPair.split("=")[1].equals("Customer")){
            ItemsList.clear();
			ItemsList.finalCost=0;            
			pw.println("<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title>");
			pw.println("<link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1>");
			pw.println("<h2>Amazing Products!</h2></header><nav><ul><li class='start selected'><a href='/A4/LoggedInUserServlet?p=Customer'>Home</a></li><li><a href='#'>Products</a></li>");
			pw.println("<li class='end'><a href='#'>Contact</a><li><a>Hello "+Credentials.getUser()+" </a></li><li><a href='/A4/ViewOrderHistoryServlet'>Order History</a></li><li><a href='/A4/TrendingServlet'>Trending</a></li><li><form method='get' class='searchform' action='#'><input type='TEXT' size='15' name='srchfield' value='Search...'></input><input class = 'button' type='submit' name='srchbox' value='GO' /></form></li><li><a href='/A4/LogoutServlet'>LOG OUT</a></li></ul></nav><img class='header-image' height='460' width='820' src='image.jpg' alt='Gadgets'/>");
			pw.println("<div id='body'><section id='content'><h3>Welcome to Best Deals</h3><h4>We offer best deals for a wide range of electronic items. Some of the stuff we sell can be categorically placed into TVs, Laptops, SmartPhones, Tablets, and related Accessories</h4>");
			pw.println("</section><aside class='sidebar'><ul><li><h4>Products</h4><ul><li><a href='/A4/RetrieveProductInfoServlet?category=TV'>TVs</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Laptop'>Laptops</a></li>");
			pw.println("<li><a href='/A4/RetrieveProductInfoServlet?category=SmartPhone'>SmartPhones</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Tablet'>Tablets</a></li></ul></li></ul></aside>");
			pw.println("<div class='clear'></div></div><footer><div class='footer-content'><h5>Prices and offers are subject to change. © 2016 Best Deals. All rights reserved. BEST DEALS, the BEST DEAL logo, the tag design, MY BEST DEAL, and BESTDEALS.COM are trademarks of Best Deals and its affiliated companies.</h5>");
			pw.println("</div></footer></div></body></html>");
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
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        try{
            processRequest(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}