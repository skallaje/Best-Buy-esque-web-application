import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteOrdersServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException, SQLException {
        String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
        CreateDBEntities en = new CreateDBEntities();
		ResultSet rs = en.getAllOrders();
        
		if(firstPair==null){
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");		
            int rec=0;
            while(rs.next()){
                rec=1;
				pw.println("<h3>"+rs.getString(2)+"</h3>");
				pw.println("<div id = 'order'>");
				pw.println("<h4> Order ID: "+ rs.getString(1) +" </h4><h4> Order Date: "+rs.getString(3)+"</h4><h4> Order Delivery Date: "+rs.getString(4)+"</h4></div>");
				pw.println("<div id = 'deleteOrderDiv'><a href='/A4/DeleteOrdersServlet?o="+rs.getString(1).trim()+"' class='button' name='deleteOrd'>DELETE</a></div>");
				pw.println("<h5>-----------------------------------------------------------------------------------------------------------------------------------------</h5>");
            }
            if(rec == 0){
                pw.println("<h3>There are no orders at the moment.</h3></center></div></div></body></html>");
            }
			pw.println("</center></div></div></body></html>");
		
		}else{
			en.deleteOrder(firstPair.split("=")[1]);
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<h4>The order has been deleted from the inventory</h4><br><a href='/A4/LoggedInUserServlet?p=Salesman' class='button' name='deleteOrd'>BACK TO HOME PAGE</a><br>");
            pw.println("</center></div></div></body></html>");
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