import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReadReviewServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();      
		if(firstPair.split("=")[0].equals("cat")){
            CreateMongoDBEntities db = new CreateMongoDBEntities();
            List<ProductReviews> l = db.getReviews(firstPair.split("=")[1].trim().replaceAll("%20"," "));
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			for(ProductReviews r:l){
                    pw.println("CATEGORY: <h3>"+r.getProdCategory()+"</h3><br><br>PRODUCT NAME:<h3>"+r.getProdName()+"</h3><br><br>PRODUCT PRICE:<h3>"+r.getProdPrice()+"</h3><br><br>");
                    pw.println("MANUFACTURER NAME:<h3>"+r.getManufacturerName()+"</h3><br><br>RETAILER NAME:<h3>BEST DEAL</h3><br><br>RETAILER CITY:<h3>CHICAGO</h3><br><br>RETAILER STATE:<h3>ILLINOIS</h3><br><br>RETAILER ZIP:<h3>60616</h3><br><br>FOR SALE: <h3>YES</h3><br><br>");
                    break;
            }
            if(l.size()>0)
                pw.println("<h5>-----------------------------------------------------------------------------------------------------------------------------------------</h5>");
            pw.println("<h4>Reviews</h4><br>");
            if(l.size()>0){
                for(ProductReviews r:l){
                    pw.println("USER NAME:<h3>"+r.getUserName()+"</h3><br><br>AGE: <h3>"+r.getUserAge()+"</h3><br><br>GENDER:<h3>"+r.getUserGender()+"</h3><br><br>OCCUPATION:<h3>"+r.getUserOccupation()+"</h3><br><br>RATING:<div class='stars'>");
                        for(int i=1;i<=Integer.valueOf(r.getReviewRating());i++){
                            pw.println("<label class='starred'></label>");
                        } 
                    pw.println("</div><br><br>REVIEW:<p><h5>"+r.getReviewText()+"</h5></p><br><br>DATE:<h3>"+r.getReviewDate()+"</h3><br><br>");
                    if(l.size()>1)
                        pw.println("<h5>-----------------------------------------------------------------------------------------------------------------------------------------</h5>");
                }
            }else{
                pw.println("<h3>There are no reviews at the moment. Be the first user to review.</h3></center></div></div></body></html>");
            }
			pw.println("</center></div></div></body></html>");
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