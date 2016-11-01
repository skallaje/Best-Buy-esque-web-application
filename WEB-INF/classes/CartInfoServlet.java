import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CartInfoServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		//ProductSerializedDataStore.loadHashMap();
		String firstPair = request.getQueryString();
		String [] nameValue = firstPair.split("=");
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();
        AppUtilities au = new AppUtilities();
		String c = au.returnProductStringID(nameValue[1].charAt(0));
		List<Product> pl = ProductSerializedDataStore.readProductDataStore(c);
		if(nameValue[1].equals("CANCEL")){
			ItemsList.clear();
			ItemsList.finalCost=0;
		}
		if(!nameValue[1].contains("IT") && !nameValue[1].equals("CANCEL")){
            for(Product p: pl){
                if(p.getId().equals(nameValue[1])){
                    Item i = new Item("it"+(ItemsList.it.size()+1), p.getId(), p.getName(), p.getName(), p.getPrice(), p.getZip(), p.getState(), p.getCity());
                    ItemsList.updateList(i);
                }
            }
		}else{
				ItemsList.deleteItem(nameValue[1]);
				ItemsList.finalCost=0;
		}
		
		pw.println("<!doctype html><html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title>");
		pw.println("<link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1>");
		pw.println("<h2>Amazing Products!</h2></header><nav><ul><li class='start selected'><a href='/A4/LoggedInUserServlet?p=Customer'>Home</a></li><li><a href='#'>Products</a></li>");
        pw.println("<li class='end'><a href='#'>Contact</a><li><a>Hello "+Credentials.getUser()+" </a></li><li><a href='/A4/ViewOrderHistoryServlet'>Order History</a></li><li><a href='/A4/TrendingServlet'>Trending</a></li><li><form method='get' class='searchform' action='#'><input type='TEXT' size='15' name='srchfield' value='Search...'></input><input class = 'button' type='submit' name='srchbox' value='GO' /></form></li><li><a href='/A4/LogoutServlet'>LOG OUT</a></li></ul></nav>");
		pw.println("<div id='body'><section id='content'>");
		if(ItemsList.it.size()>1)
			pw.println("<h3>You ordered "+ItemsList.it.size()+ " items.</h3> <h4> Here are the item details</h4>");
		else if(ItemsList.it.size()==1)
			pw.println("<h3>You ordered "+ItemsList.it.size()+ " item.</h3> <h4> Here are the item details</h4>");
		else
			pw.println("<h3>Your cart is empty.</h3>");
		for(ItemOrder io: ItemsList.it){
			pw.println("<div id = 'item'><div id = 'details'>");
			pw.println("<h4> Price: $"+ io.item.getCost() +" </h4><h4> Item ID: "+io.item.getItemID().toUpperCase()+"</h4><h4> Name: "+io.item.getLongDescription()+" "+io.item.getItemCategory()+"</h4>");
			pw.println("<a href='/A4/CartInfoServlet?o="+io.item.getItemID().toUpperCase()+"' class='button' name='deleteitem'>DELETE ITEM</a></div></div>");
		}
		if(ItemsList.it.size()>0){
			pw.println("<br><a href='/A4/CartInfoServlet?o=CANCEL' class='button' name='cancel'>CANCEL ORDER</a>");
			pw.println("<a href='/A4/CheckOutOrderServlet' class='button' name='checkout'>CHECKOUT</a>");
		}
		pw.println("</section><aside class='sidebar'><ul><li><h4>Products</h4><ul><li><a href='/A4/RetrieveProductInfoServlet?category=TV'>TVs</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Laptop'>Laptops</a></li>");
        pw.println("<li><a href='/A4/RetrieveProductInfoServlet?category=SmartPhone'>SmartPhones</a></li><li><a href='/A4/RetrieveProductInfoServlet?category=Tablet'>Tablets</a></li></ul></li></ul></aside>");
    	pw.println("<div class='clear'></div></div><footer><div class='footer-content'><h5>Prices and offers are subject to change. © 2016 Best Deals. All rights reserved. BEST DEALS, the BEST DEAL logo, the tag design, MY BEST DEAL, and BESTDEALS.COM are trademarks of Best Deals and its affiliated companies.</h5>");
        pw.println("</div></footer></div></body></html>");
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