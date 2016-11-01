import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.io.*;
import java.sql.*;

public class ProductSerializedDataStore{

	public static Map<String, List<Product>> sm = new HashMap<String, List<Product>>();
    public static boolean loaded = false;

	public static void prepareDataStore(){
		for(String key: new String[]{"TV","Laptop","SmartPhone","Tablet"}){
                if(sm.size()==0){
                    sm.put("TV", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductTVCatalog.xml").getProducts());
                    sm.put("Laptop", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductLaptopCatalog.xml").getProducts());
                    sm.put("SmartPhone", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductSmartPhoneCatalog.xml").getProducts());
                    sm.put("Tablet", new SaxParser4ProductXMLdataStore("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\ProductTabletCatalog.xml").getProducts());
                    loadProductsTable();
                }
		}
	}

    public static void populateSerializedDataStore(String category, List<Product> p){
        sm.put(category, p);
    }

    public static List<Product> readProductDataStore(String category){
       return sm.get(category);
    }
    
    public static void loadProductsTable(){
        CreateDBEntities en = new CreateDBEntities();
        for(String key: new String[]{"TV","Laptop","SmartPhone","Tablet"}){
                for(Product p:sm.get(key)){
                    en.insertProduct(p.getId(), p.getName(), String.valueOf(p.getPrice()), p.getZip(), p.getState(), p.getCity(), p.getRetailer(), key, p.getImage(), p.getCondition(), p.getAccessories().get(0), p.getAccessories().get(1));
            }
		}
    }
    
    public static void loadHashMap(){
        if(loaded == false){
            try{
                sm.clear();
                int count=0;
                CreateDBEntities en = new CreateDBEntities();
                for(String key: new String[]{"TV","Laptop","SmartPhone","Tablet"}){
                    ResultSet rs = en.getProductsByCategory(key);
                    List<Product> l = new CopyOnWriteArrayList<Product>();
                    while(rs.next()){
                        Product p = new Product();
                        p.setId(rs.getString(1));
                        p.setRetailer(rs.getString(7));
                        p.setZip(rs.getString(4));
                        p.setState(rs.getString(5));
                        p.setCity(rs.getString(6));
                        p.setName(rs.getString(2));
                        p.setImage(rs.getString(9));
                        p.setCondition(rs.getString(10));
                        p.setPrice(Integer.parseInt(rs.getString(3)));
                        p.getAccessories().add(rs.getString(11));
                        p.getAccessories().add(rs.getString(12));
                        l.add(p);
                        count++;
                    }
                    sm.put(key, l);            
                }
                if(count==0){
                    sm.clear();
                    prepareDataStore();
                }else{
                    loaded = true;
                }
            }catch(Exception e){
                    e.printStackTrace();
            }
        }
    }
}