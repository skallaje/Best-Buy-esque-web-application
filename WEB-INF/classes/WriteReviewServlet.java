import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class WriteReviewServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException {
		String firstPair = request.getQueryString();
		response.setContentType("text/html");
	    PrintWriter pw = response.getWriter();	
        AppUtilities au = new AppUtilities();        
		if(firstPair.split("=")[0].equals("cat")){
            WriteReviews.p = au.getProductInfo(firstPair.split("=")[1]);
            WriteReviews.prodname = WriteReviews.p.getName();
            WriteReviews.category = au.returnProductStringID(firstPair.split("=")[1].charAt(0));
            WriteReviews.price = "$"+String.valueOf(WriteReviews.p.getPrice());
            WriteReviews.mn = WriteReviews.prodname;
            if(WriteReviews.mn.contains(" "))
                WriteReviews.mn = WriteReviews.mn.split(" ")[0];
            WriteReviews.date = WriteReviews.dateFormat.format(WriteReviews.d);
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<center><form method='post' action='/A4/WriteReviewServlet?p=write'><h4>Write your review info</h4><br>CATEGORY: <h3>"+WriteReviews.category+"</h3><br><br>PRODUCT NAME:<h3>"+WriteReviews.prodname+"</h3><br><br>PRODUCT PRICE:<h3>"+WriteReviews.price+"</h3><br><br>");
			pw.println("MANUFACTURER NAME:<h3>"+WriteReviews.mn+"</h3><br><br>RETAILER NAME:<h3>BEST DEAL</h3><br><br>RETAILER CITY:<h3>CHICAGO</h3><br><br>RETAILER STATE:<h3>ILLINOIS</h3><br><br>RETAILER ZIP:<h3>60616</h3><br><br>FOR SALE: <h3>YES</h3><br><br>");
            pw.println("USER NAME:<h3>"+Credentials.getUser()+"</h3><br><br>AGE: <input type='TEXT' size='15' name='uage'/><br><br>GENDER:<select name='ugender'><option value='Male'>Male</option><option value='Female'>Female</option><option value='Unspecified'>I don't prefer to answer</option></select><br><br>OCCUPATION:<input type='TEXT' size='30' name='uoccupation'/><br><br>RATING:<div class='stars'>");
            pw.println("<input class='star star-5' id='star-5' type='radio' name='star' value='5'/><label class='star star-5' for='star-5'></label><input class='star star-4' id='star-4' type='radio' name='star' value='4'/><label class='star star-4' for='star-4'></label>");
            pw.println("<input class='star star-3' id='star-3' type='radio' name='star' value='3'/><label class='star star-3' for='star-3'></label><input class='star star-2' id='star-2' type='radio' name='star' value='2'/>");
            pw.println("<label class='star star-2' for='star-2'></label><input class='star star-1' id='star-1' type='radio' name='star' value='1'/><label class='star star-1' for='star-1'></label></div><br><br>REVIEW:<textarea rows='5' cols='50' maxlength='500' name='ureview'></textarea><br><br>DATE:<h3>"+WriteReviews.date+"</h3><br><br>");
			pw.println("<input class = 'button' type='submit' value='SUBMIT' /></form></center></div></div></body></html>");
        }else{
            String rating = request.getParameter("star");
            if(rating==null)
                rating = "0";
            
            WriteReviews.review = new ProductReviews(WriteReviews.prodname, WriteReviews.category, WriteReviews.price, "BEST DEAL", "60616", "Chicago", "Illinois", "Yes", WriteReviews.mn, Credentials.getUser(), request.getParameter("uage"), request.getParameter("uoccupation"), request.getParameter("ugender"), request.getParameter("ureview"), WriteReviews.date, rating);
            CreateMongoDBEntities db = new CreateMongoDBEntities();
			db.insertReview(WriteReviews.review);
			pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
			pw.println("<h3>The review has been updated in the inventory</h3><br><br><a href='/A4/RetrieveProductInfoServlet?category="+WriteReviews.category+"' class='button' name='deleteOrd'>BACK TO "+WriteReviews.category+" SELECTION</a></center></div></div></body></html>");
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