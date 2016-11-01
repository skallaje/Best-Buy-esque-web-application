import java.io.PrintWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException, SQLException {
        String userid = request.getParameter("userid");
        String password = request.getParameter("password");
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
        
        CreateDBEntities en = new CreateDBEntities();
        
        if(userid != null &&
            password != null) {
                ResultSet rs = en.checkUserExists(userid);
				if(en.resultSetSize(rs)!=0 && rs.getString(2).equals(password)){
                    
					String utype = rs.getString(3);
                    
                    Credentials.setUser(userid);
					if(utype.equals("Salesman")){						
                        request.getRequestDispatcher("LoggedInUserServlet?p=Salesman").forward(request, response);
					}
					if(utype.equals("StoreManager")){						
                        request.getRequestDispatcher("LoggedInUserServlet?p=StoreManager").forward(request, response);
					}
					if(utype.equals("Customer")){
						request.getRequestDispatcher("LoggedInUserServlet?p=Customer").forward(request, response);
                    }
                } else {
					pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
					pw.println("<h3>Invalid user credentials. Please try again.</h3></center></div></div></body></html>");
                }
        }  else {
				pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
				pw.println("<h3>Please enter username and password.</h3></center></div></div></body></html>");
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