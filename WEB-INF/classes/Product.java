import java.util.*;
import java.io.*;


public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
    String retailer;
    String name;
    String id;
    String image;
    String condition;
	List<String> accessories;
    String zip;
    String city, state;
    int price;
	
    public Product(){
		accessories=new ArrayList<String>();
    }
    
    public void setZip(String z){
        this.zip=z;
    }
    
    public void setState(String s){
        this.state=s;
    }
    
    public void setCity(String c){
        this.city=c;
    }
    
    public String getZip() {
    	return this.zip;
    }
    
    public String getState() {
    	return this.state;
    }
    
    public String getCity() {
    	return this.city;
    }

    public void setId(String id) {
    	this.id = id;
    }
	
	public String getId() {
    	return this.id;
    }

	public void setRetailer(String retailer) {
		this.retailer = retailer;
	}
	
	public String getRetailer() {
		return this.retailer;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}
	
	public String getCondition() {
		return this.condition;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public int getPrice() {
		return this.price;
	}

	public List<String> getAccessories() {
		return accessories;
	}
	
	public void setAccessories(String a1, String a2){
        if(!this.getAccessories().contains(a1.trim())){
            accessories.add(a1);
        }
        if(!this.getAccessories().contains(a2.trim())){
            accessories.add(a2);
        }
	}
    
    public String getImage(){
        return this.image;
    }
}
