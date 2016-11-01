import java.util.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class FinalOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String orderConfirmationID="";
	String username;
	Date orderDate;
    String deliveryDate;
    String creditCardNumber="";
	String message="Order would be delivered to you on ";
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    Calendar c = Calendar.getInstance();
	
	
	public FinalOrder(String s, Date d, String card){
		this.orderConfirmationID = getSaltString();
		this.username = s;
		this.orderDate = d;
        this.creditCardNumber=card;
        setDeliveryDate();
	}

	public  String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
    
    public void setDeliveryDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:00");
		c.setTime(orderDate);
		c.add(Calendar.DATE, 14); 
		deliveryDate = sdf.format(c.getTime()); 
	}
    
    public String getDeliveryDate(){
		return this.deliveryDate.trim();
	}
    
    public String getUserName(){
		return this.username.trim();
	}
    
    public String getCreditCardNumber(){
		return this.creditCardNumber.trim();
	}
	
	public String getOrderConfirmationID(){
		return this.orderConfirmationID.trim();
	}
	
	public String getOrderMessage(){
		return this.message+this.deliveryDate.substring(0,10)+" at "+this.deliveryDate.substring(11,16);
	}
	
	public String getOrderDate(){
		return dateFormat.format(this.orderDate);
	}
}