import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class AjaxUtilityServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		response.setContentType("text/xml");
        String firstPair = request.getQueryString();
        List<Product> l = matchData(firstPair.split("=")[1].toLowerCase());
        StringBuilder s = new StringBuilder();
        for(Product p:l){
            s.append("<product>");
            s.append("<id>"+p.getId()+"</id>");
            s.append("<productName>"+p.getName()+"</productName>");
            s.append("</product>");
        }
        response.getWriter().write("<products>" + s.toString() + "</products>");
        		                
    }
    
    protected List<Product> matchData(String match){
        List<Product> pl = new ArrayList<Product>();
        for(String key: new String[]{"TV","Laptop","SmartPhone","Tablet"}){
            List<Product> l = ProductSerializedDataStore.readProductDataStore(key);
            for(Product p:l){
                if(p.getName().toLowerCase().startsWith(match))
                    pl.add(p);
            }  
		}
        return pl;
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
            processRequest(request, response);
    } 
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
            processRequest(request, response);
    }
}