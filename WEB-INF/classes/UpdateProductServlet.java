import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UpdateProductServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
		//ProductSerializedDataStore.loadHashMap();
        AppUtilities au = new AppUtilities();
		if(firstPair==null){
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'>");
			pw.println("<center><form method='get' action='/A4/UpdateProductServlet'><h4>Enter the product details</h4><br>CATEGORY:<select name='category'><option value='TV'>TV</option><option value='Laptop'>Laptop</option><option value='SmartPhone'>SmartPhone</option><option value='Tablet'>Tablet</option></select><br><br>ID:<input type='TEXT' size='15' name='ID'/><br><br>");
			pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
		}else if(!firstPair.split("=")[1].equals("update")){
			String [] qs = firstPair.split("&");
			UpdateProduct.fs = qs[0].split("=")[1];
			if(qs[1].split("=").length==1){
				pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'>");
				pw.println("<center><form method='get' action='/A4/UpdateProductServlet'><h4>Enter the product details</h4><br>CATEGORY:<select name='category'><option value='TV'>TV</option><option value='Laptop'>Laptop</option><option value='SmartPhone'>SmartPhone</option><option value='Tablet'>Tablet</option></select><br><br><h4>Please enter the Product ID</h4><br>ID:<input type='TEXT' size='15' name='ID'/><br><br>");
				pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
			}else{
				UpdateProduct.pr = au.checkID(UpdateProduct.fs,qs[1].split("=")[1]);
				if(UpdateProduct.pr==null){
					pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
					pw.println("<h3>Invalid Product ID</h3></center></div></div></body></html>");
				}else{
					pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
					pw.println("<center><form method='post' action='/A4/UpdateProductServlet?p=update'><h4>Enter the product details</h4><br>NAME:<input type='TEXT' size='15' name='name' value='"+UpdateProduct.pr.getName()+"'/><br><br>CONDITION:<select name='condition'><option value='New'>New</option><option value='Refurbished'>Refurbished</option></select><br><br>");
					pw.println("PRICE:<input type='TEXT' size='15' name='prc' value='"+UpdateProduct.pr.getPrice()+"'></input><br><br>ACCESSORY 1:<input type='TEXT' size='15' name='acc1' value='"+UpdateProduct.pr.getAccessories().get(0)+"'/><br><br>ACCESSORY 2:<input type='TEXT' size='15' name='acc2' value='"+UpdateProduct.pr.getAccessories().get(1)+"'/><br><br>RETAILER:<input type='TEXT' size='15' name='retailer' value='"+UpdateProduct.pr.getRetailer()+"'/><br><br>ZIP:<input type='TEXT' size='15' name='zip' value='"+UpdateProduct.pr.getZip()+"'/><br><br>STATE:<input type='TEXT' size='15' name='state' value='"+UpdateProduct.pr.getState()+"'/><br><br>CITY:<input type='TEXT' size='15' name='city' value='"+UpdateProduct.pr.getCity()+"'/><br><br>");
					pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
				}
			}
		}else{
				List<Product> pl = ProductSerializedDataStore.readProductDataStore(UpdateProduct.fs);
				for(Product p:pl){
					if(p.getId().equals(UpdateProduct.pr.getId())){
						p.setRetailer(request.getParameter("retailer"));
                        p.setZip(request.getParameter("zip"));
                        p.setState(request.getParameter("state"));
                        p.setCity(request.getParameter("city"));
						p.setName(request.getParameter("name"));
						p.setPrice(Integer.parseInt(request.getParameter("prc")));
						p.setCondition(request.getParameter("condition"));
						p.setAccessories(request.getParameter("acc1"),request.getParameter("acc2"));
                        CreateDBEntities en = new CreateDBEntities();
                        en.updateProductInfo(request.getParameter("name"), request.getParameter("prc"), request.getParameter("condition"), request.getParameter("acc1"), request.getParameter("acc2"), request.getParameter("retailer"), request.getParameter("zip"), request.getParameter("state"), request.getParameter("city"), p.getId());
					}
				}                
				ProductSerializedDataStore.populateSerializedDataStore(UpdateProduct.fs, pl);
				pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
				pw.println("<h3>The "+UpdateProduct.fs+" has been updated in the inventory</h3><br><a href='/A4/LoggedInUserServlet?p=StoreManager' class='button' name='deleteOrd'>BACK TO HOME PAGE</a></center></div></div></body></html>");				
			}
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