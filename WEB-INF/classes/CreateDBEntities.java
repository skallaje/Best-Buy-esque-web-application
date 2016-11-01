import java.sql.*;

public class CreateDBEntities {
   static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
   static final String DB_URL = "jdbc:mysql://localhost:3306/BestDealDatabase?useSSL=false";
   static final String USER = "root";
   static final String PASS = "root";
   Connection conn;
   
   public CreateDBEntities(){
       try{
           Class.forName("com.mysql.jdbc.Driver");
           conn = DriverManager.getConnection(DB_URL, USER, PASS);
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public void create_database() {
   Statement stmt = null;
   try{
      //System.out.println("Creating database...");
      stmt = conn.createStatement();      
      String sql = "CREATE DATABASE BestDealDatabase";
      stmt.executeUpdate(sql);
      //System.out.println("Database created successfully...");
   }catch(Exception e){
      e.printStackTrace();
   }
}

public void insertCredentials(String username, String password, String usertype) {
   try{
      //System.out.println("Creating database...");
      String sql = "INSERT INTO Credentials " + "VALUES (?,?,?);";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, username);
      ps.setString(2, password);
      ps.setString(3, usertype);
      ps.execute();
      //System.out.println("Inserted successfully...");
   }catch(Exception e){
      e.printStackTrace();
   }
}

public void insertSoldItems(String itemid, String prodid, String prodname, String itemprice, String itemzip, String itemstate, String itemcity) {
   try{
      //System.out.println("Creating database...");
      String sql = "INSERT INTO SoldItems " + "VALUES (?,?,?,?,?,?,?);";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, itemid);
      ps.setString(2, prodid);
      ps.setString(3, prodname);
      ps.setString(4, itemprice);
      ps.setString(5, itemzip);
      ps.setString(6, itemstate);
      ps.setString(7, itemcity);
      ps.execute();
      //System.out.println("Inserted successfully...");
   }catch(Exception e){
      e.printStackTrace();
   }
}

public ResultSet getMaxSoldItems(String zip) {
   ResultSet rs = null;
   String sql="";
   try{
      //System.out.println("Creating database...");
      if(zip.equals("zip"))
        sql = "select itemzip, count(itemzip) from SoldItems group by itemzip order by count(itemzip) desc limit 5";
      else
        sql = "select productName, count(productName) from soldItems group by productName order by count(productName) desc limit 5";
      PreparedStatement ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
   return rs;
}

public ResultSet checkUserExists(String username) {
   ResultSet rs = null;
   try{
      //System.out.println("Creating database...");
      String sql = "Select * from Credentials where username = '"+username+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
   return rs;
}

public void insertOrder(String orderid, String username, String orderdate, String orderdeliverydate, String cardnum, String msg) {
   try{
      //System.out.println("Creating database...");
      String sql = "INSERT INTO finalorder " + "VALUES (?,?,?,?,?,?);";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, orderid);
      ps.setString(2, username);
      ps.setString(3, orderdate);
      ps.setString(4, orderdeliverydate);
      ps.setString(5, cardnum);
      ps.setString(6, msg);
      ps.execute();
      //System.out.println("Inserted successfully...");
   }catch(Exception e){
      e.printStackTrace();
   }
}

public void insertProduct(String id, String name, String price, String zip, String state, String city, String retailer, String category, String image, String pstate, String a1, String a2) {
   try{
      //System.out.println("Creating database...");
      String sql = "INSERT INTO Products " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?);";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.setString(1, id);
      ps.setString(2, name);
      ps.setString(3, price);
      ps.setString(4, zip);
      ps.setString(5, state);
      ps.setString(6, city);
      ps.setString(7, retailer);
      ps.setString(8, category);
      ps.setString(9, image);
      ps.setString(10, pstate);
      ps.setString(11, a1);
      ps.setString(12, a2);
      ps.execute();
      //System.out.println("Inserted successfully...");
   }catch(Exception e){
      e.printStackTrace();
   }
}

public void deleteProduct(String id) {
   try{
      //System.out.println("Creating database...");
      String sql = "delete from Products where productID = '"+id+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.execute();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
}

public void updateProductInfo(String name, String price, String condition, String a1, String a2, String retailer, String zip, String state, String city, String id) {
   try{
      //System.out.println("Creating database...");
      String sql = "update Products set productName = '"+name+"', price = '"+price+"', prodState = '"+condition+"',"+ 
      "accessory1 = '"+a1+"', accessory2 = '"+a2+"', retailer = '"+retailer+"', zip = '"+zip+"', state = '"+state+"', city = '"+city+"' where productID= '"+id+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.execute();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
}

public ResultSet getProductsByCategory(String category){
   ResultSet rs = null;
   try{
      //System.out.println("Creating database...");
      String sql = "Select * from Products where category ='"+category+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
   return rs; 
}

public ResultSet checkOrderExists(String username, String orderid) {
   ResultSet rs = null;
   try{
       String sql;
      //System.out.println("Creating database...");
      if(orderid.equals("N/A"))
        sql = "Select * from finalorder where username = '"+username+"'";
      else
        sql = "Select * from finalorder where orderConfirmationID= '"+orderid+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
   return rs;
}

public ResultSet getAllOrders() {
   ResultSet rs = null;
   try{
      //System.out.println("Creating database...");
      String sql = "Select * from finalorder";
      PreparedStatement ps = conn.prepareStatement(sql);
      rs = ps.executeQuery();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
   return rs;
}

public void deleteOrder(String orderid) {
   try{
      //System.out.println("Creating database...");
      String sql = "delete from finalorder where orderConfirmationID= '"+orderid+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.execute();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
}

public void updateOrderInfo(String orderid, String deldate, String msg) {
   try{
      //System.out.println("Creating database...");
      String sql = "update finalorder set message = '"+msg+"', orderdeliverydate = '"+deldate+"' where orderConfirmationID= '"+orderid+"'";
      PreparedStatement ps = conn.prepareStatement(sql);
      ps.execute();      
      //System.out.println("rs "+rs);
   }catch(Exception e){
      e.printStackTrace();
   }
}

public int resultSetSize(ResultSet res){
    int size= 0;
    try{
        if (res != null){  
            res.beforeFirst();  
            res.last();  
            size = res.getRow();  
        }
    }catch(Exception e){
        e.printStackTrace();
   }
    return size;
}



}