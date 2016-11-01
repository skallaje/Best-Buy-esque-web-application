import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AddProductServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
		//ProductSerializedDataStore.loadHashMap();		
		if(firstPair==null){
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<center><form method='post' action='/A4/AddProductServlet?p=add'><h4>Enter the product details</h4><br>CATEGORY:<select name='category'><option value='TV'>TV</option><option value='Laptop'>Laptop</option><option value='SmartPhone'>SmartPhone</option><option value='Tablet'>Tablet</option></select><br><br>NAME:<input type='TEXT' size='15' name='name'/><br><br>IMAGE:<input type='TEXT' size='15' name='img'></input><br><br>CONDITION:<select name='condition'><option value='New'>New</option><option value='Refurbished'>Refurbished</option></select><br><br>");
			pw.println("PRICE:<input type='TEXT' size='15' name='prc'></input><br><br>ACCESSORY 1:<input type='TEXT' size='15' name='acc1'/><br><br>ACCESSORY 2:<input type='TEXT' size='15' name='acc2'/><br><br>RETAILER:<input type='TEXT' size='15' name='retailer'/><br><br>ZIP:<input type='TEXT' size='15' name='zip'/><br><br>STATE:<input type='TEXT' size='15' name='state'/><br><br>CITY:<input type='TEXT' size='15' name='city'/><br><br>");
			pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
		}else{
            AppUtilities au = new AppUtilities();
			Product p = au.returnProductObject(request.getParameter("category"));
			p.setId(au.returnProductStringID(request.getParameter("category")).toString()+(au.returnNewID(request.getParameter("category"))+1));
			p.setRetailer(request.getParameter("retailer"));
            p.setZip(request.getParameter("zip"));
            p.setState(request.getParameter("state"));
            p.setCity(request.getParameter("city"));
			p.setImage("iphone.jpg");
			p.setName(request.getParameter("name"));
			p.setPrice(Integer.parseInt(request.getParameter("prc")));
			p.setCondition(request.getParameter("condition"));
            p.setAccessories(request.getParameter("acc1"),request.getParameter("acc2"));
            List<Product> pl = ProductSerializedDataStore.readProductDataStore(request.getParameter("category"));
			pl.add(p);
			ProductSerializedDataStore.populateSerializedDataStore(request.getParameter("category"), pl);
            CreateDBEntities en = new CreateDBEntities();
            en.insertProduct(p.getId(), p.getName(), String.valueOf(p.getPrice()), p.getZip(), p.getState(), p.getCity(), p.getRetailer(), request.getParameter("category"), p.getImage(), p.getCondition(), p.getAccessories().get(0), p.getAccessories().get(1));
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<h3>The "+request.getParameter("category")+" has been added to the inventory</h3><br><a href='/A4/LoggedInUserServlet?p=StoreManager' class='button' name='deleteOrd'>BACK TO HOME PAGE</a></center></div></div></body></html>");
		}
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