import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DisplayAllProductsServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();		
		//ProductSerializedDataStore.loadHashMap();
		pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");		
		for(String key: new String[]{"TV","Laptop","SmartPhone","Tablet"}){
			for(Product p : ProductSerializedDataStore.readProductDataStore(key)){
				pw.println("<h3>"+key+"</h3>");
				pw.println("<div id = 'product'>");
				pw.println("<h4> Price: "+ p.getPrice() +" </h4><h4> ID: "+p.getId()+"</h4><h4> Name: "+p.getName()+"</h4></div>");
				pw.println("<h5>-----------------------------------------------------------------------------------------------------------------------------------------</h5>");
			}
		}
		pw.println("</center></div></div></body></html>");		
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