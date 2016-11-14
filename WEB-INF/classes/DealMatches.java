import java.io.*;
import java.util.*;

public class DealMatches {
	
	public List<String> getWeeklyDeals(){
		BufferedReader br1;
		String line;
        List<String> l = new ArrayList<String>();
		try {
			br1 = new BufferedReader(new FileReader("C:\\apache-tomcat-7.0.34\\webapps\\A5\\DealMatches.txt"));
			while((line = br1.readLine()) != null){
				l.add(line);
			}
			br1.close();
		}catch (Exception e) {
            e.printStackTrace();
        }
        return l;
	}
    
    public List<String> getWeeklyDealIDs(){
		BufferedReader br1;
		String line;
        List<String> l = new ArrayList<String>();
		try {
			br1 = new BufferedReader(new FileReader("C:\\apache-tomcat-7.0.34\\webapps\\A5\\IDMatches.txt"));
			while((line = br1.readLine()) != null){
				l.add(line);
			}
			br1.close();
		}catch (Exception e) {
            e.printStackTrace();
        }
        return l;
	}
}