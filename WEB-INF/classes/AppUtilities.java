import java.io.PrintWriter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

public class AppUtilities{

   public int returnNewID(String s){
		List<Product> l = ProductSerializedDataStore.readProductDataStore(s);
		List<Integer> li = new ArrayList<Integer>();
		for(Product p:l){
			li.add(Integer.parseInt(p.getId().substring(1)));
		}
		return li.get(li.size()-1);
	}
    
    public Product checkID(String c, String s){
		List<Product> lp = ProductSerializedDataStore.readProductDataStore(c);
		for(Product p:lp){
			if(p.getId().equals(s)){
				return p;
			}
		}
		return null;
	}

	public Product returnProductObject(String s){
		switch(s){
			case "TV":return new TV();
			case "Laptop":return new Laptop();
			case "SmartPhone":return new SmartPhone();
			case "Tablet":return new Tablet();
		}
		return null;
	}
	
	public Character returnProductStringID(String s){
		switch(s){
			case "TV":return 't';
			case "Laptop":return 'l';
			case "SmartPhone":return 's';
			case "Tablet":return 'b';
		}
		return ' ';
	}
    
    public Product getProductInfo(String id){        
        //ProductSerializedDataStore.loadHashMap();
        List<Product> pl = ProductSerializedDataStore.readProductDataStore(returnProductStringID(id.charAt(0)));
				for(Product p:pl){
					if(p.getId().equals(id)){
						return p;
					}
				}
        return null;
    }
    
    public String returnProductStringID(Character s){
		switch(s){
			case 't':return "TV";
			case 'l':return "Laptop";
			case 's':return "SmartPhone";
			case 'b':return "Tablet";
		}
		return null;
	}
    
    public long returnDaysGap(String d){
        long diff=0;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date1 = dateFormat.parse(d);
            Date date2 = new Date();
            diff=TimeUnit.DAYS.convert((date2.getTime() - date1.getTime()), TimeUnit.MILLISECONDS);
        }catch(Exception e){
            e.printStackTrace();
        }		
		return diff;
	}
    
    public List<Product> deleteProduct(String s, String c){
		List<Product> lp = ProductSerializedDataStore.readProductDataStore(c);
        CreateDBEntities en = new CreateDBEntities();
		for(Product p:lp){
			if(p.getId().equals(s)){
				lp.remove(p);
                en.deleteProduct(p.getId());
			}
		}
		return lp;
	}
    
    public void mostLikedProducts(PrintWriter pw){
        int rec=0;
        CreateMongoDBEntities db = new CreateMongoDBEntities();
        MultiMap m = db.mostLikedProducts();
        Map<String, Float> sm = m.getSortedMap(m);
        pw.println("<h3>Most Liked Products</h3>");
        pw.println("<table><tr><th>Products</th><th>Review Rating</th></tr>"); 
        int count=0;
        for(Map.Entry e : sm.entrySet()) {
            if(count==5)
                break;
            
            rec=1;
            pw.println("<tr><td>"+e.getKey()+"</td><td>"+ String.format("%.1f", e.getValue())+"</td></tr>");
            count++;
        }
        pw.println("</table>");
        if(rec == 0){
            pw.println("<h3>There are no orders at the moment.</h3></center></div></div></body></html>");
		}        
    }
    
    public void zipMaxSoldProducts(String s, PrintWriter pw) throws java.sql.SQLException {
        int rec=0;
        CreateDBEntities en = new CreateDBEntities();
        ResultSet rs = en.getMaxSoldItems(s);
        if(s.equals("zip")){
            pw.println("<h3>Zip Codes - Max Sold Products</h3>");
            pw.println("<table><tr><th>Zip Code</th><th>Count</th></tr>");
        }else{
            pw.println("<h3>Products - Most Sold</h3>");
            pw.println("<table><tr><th>Products</th><th>Count</th></tr>");
        }
        while(rs.next()){
            rec=1;
            pw.println("<tr><td>"+rs.getString(1)+"</td><td>"+rs.getString(2)+"</td></tr>");
        }        
        pw.println("</table>");
        if(rec == 0){
            pw.println("<h3>There are no orders at the moment.</h3></center></div></div></body></html>");
		}
    }

}