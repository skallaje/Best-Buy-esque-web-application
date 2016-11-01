import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
        
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        
		pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
		pw.println("<h3>You have logged out.</h3><br><a href='/A4/signin.html' class='button' name='deleteOrd'>BACK TO LOGIN PAGE</a></center></div></div></body></html>");
    } 
  
    protected void showPage(HttpServletResponse response, String s)
    throws ServletException, java.io.IOException {
       response.setContentType("text/html");
	   PrintWriter pw = response.getWriter();
	   pw.println(s);	   
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