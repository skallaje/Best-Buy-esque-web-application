import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateOrdersServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException, SQLException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
        CreateDBEntities en = new CreateDBEntities();
        
		if(firstPair==null){
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'>");
			pw.println("<center><form method='get' action='/A4/UpdateOrdersServlet'><h4>Enter the Order ID Info</h4><br>ORDER ID: <input type='TEXT' size='15' name='ID'/><br><br>");
			pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
		}else if(!firstPair.split("=")[1].equals("update")){
                UpdateOrders.id=firstPair.split("=")[1].trim();
                ResultSet rs = en.checkOrderExists("N/A", UpdateOrders.id);                
				if(en.resultSetSize(rs)==0){
					pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
					pw.println("<h3>Invalid Order ID</h3></center></div></div></body></html>");
				}else{
					pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
					pw.println("<center><form method='post' action='/A4/UpdateOrdersServlet?p=update'><h4>Enter the order details</h4><br>ORDER ID: <h3>"+rs.getString(1)+"</h3><br>USER ASSOCIATED: <h3>"+rs.getString(2)+"</h3><br><br>ORDER DEL. DATE: <input type='TEXT' size='15' name='deldate' value='"+rs.getString(4)+"'/><br><br>");
					pw.println("ORDER MESSAGE: <textarea rows='5' cols='50' maxlength='500' name='ordmsg'>"+rs.getString(6)+"</textarea><br><br>");
					pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
				}
		}else{
                en.updateOrderInfo(UpdateOrders.id, request.getParameter("deldate"), request.getParameter("ordmsg"));
				pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
				pw.println("<h3>The order has been updated in the inventory</h3><br><a href='/A4/LoggedInUserServlet?p=Salesman' class='button' name='deleteOrd'>BACK TO HOME PAGE</a></center></div></div></body></html>");				
			}
		}
  
    protected void showPage(HttpServletResponse response, String s)
    throws ServletException, java.io.IOException, SQLException {
       response.setContentType("text/html");
	   PrintWriter pw = response.getWriter();
	   pw.println(s);	   
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