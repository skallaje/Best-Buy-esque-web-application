import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.io.*;

public class SignUpServlet extends HttpServlet {
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, SQLException {
        String userid = request.getParameter("user");
        String password = request.getParameter("pass");
		String userType = request.getParameter("userTypes");	
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		
        if(userid != null && userid.length() != 0) {
            userid = userid.trim();
        }
        if(password != null && password.length() != 0) {
            password = password.trim();
        }
		if(userType != null) {
            userType = userType.trim();
        }
		
        CreateDBEntities en = new CreateDBEntities();
		
        if((userid!=null && password!=null) && (!userid.equals("") &&
            !password.equals(""))){
				if(en.resultSetSize(en.checkUserExists(userid))==0){
                    en.insertCredentials(userid, password, userType);
                    Credentials.setUser(userid);
					if(userType.equals("Salesman")){
                        request.getRequestDispatcher("LoggedInUserServlet?p=Salesman").forward(request, response);}
					if(userType.equals("StoreManager")){
                        request.getRequestDispatcher("LoggedInUserServlet?p=StoreManager").forward(request, response);}
					if(userType.equals("Customer")){ItemsList.clear();
						ItemsList.finalCost=0;
						request.getRequestDispatcher("LoggedInUserServlet?p=Customer").forward(request, response);
                    }
			}else{
				pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
				pw.println("<h3>User ID already taken. Try with a different one.</h3></center></div></div></body></html>");
			}
                
        } else {
				pw.println("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title>BEST DEALS</title><link rel='stylesheet' href='styles.css' type='text/css' /></head><body><div id='container'><header><h1><a href='/'>Best <span>Deals</span></a></h1></header><div id='body'><center>");
				pw.println("<h3>Please enter username and password.</h3></center></div></div></body></html>");
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
            processRequest(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    } 
	
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try{
            processRequest(request, response);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}