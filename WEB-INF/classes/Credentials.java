import java.util.HashMap;
import java.util.Map;
import java.io.*;


public class Credentials {
	
	static Map<String, String> up = new HashMap<String, String>();
	static Map<String, String> m = new HashMap<String, String>();
	static String thisUser;
	
	public static void populateHashMap(){
		BufferedReader br1;
		BufferedReader br2;
		String line;
		try {
			br1 = new BufferedReader(new FileReader("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\Credentials1.txt"));
			while((line = br1.readLine()) != null){
				up.put(line.split(" ")[0], line.split(" ")[1]);
			}
			br2 = new BufferedReader(new FileReader("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\Credentials2.txt"));
			while((line = br2.readLine()) != null){
				m.put(line.split(" ")[0], line.split(" ")[1]);
			}
			br1.close();
			br2.close();
		}catch (Exception e) {
            e.printStackTrace();
        }
	}	
		
	
	public static String userExists(String s){
		if(up.get(s)!=null)setUser(s);
		return up.get(s);
	}
	
	public static String getUser(){
		return thisUser;
	}
    
    public static void setUser(String s){
		thisUser=s;
	}
	
	public static String userType(String s){
		return m.get(s);
	}
	
	public static void updateHashMap(String key, String value, String utype){
		BufferedWriter writer1 = null;
		BufferedWriter writer2 = null;
		up.put(key, value);
		utype = returnUserType(utype);
		m.put(key, utype);
		try {
            writer1 = new BufferedWriter(new FileWriter("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\Credentials1.txt", true));
			writer1.append('\n');
            writer1.append(key+" "+value);
			writer2 = new BufferedWriter(new FileWriter("C:\\apache-tomcat-7.0.34\\webapps\\A4\\WEB-INF\\Credentials2.txt", true));
			writer2.append('\n');
            writer2.append(key+" "+utype);
			writer1.close();
			writer2.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static String returnUserType(String c){
			switch(c){
				case "s":return "Salesman";
				case "c":return "Customer";
				case "r":return "Store Manager";			
			}
		return "";
	}
}