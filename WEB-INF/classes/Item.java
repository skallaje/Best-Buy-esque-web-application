public class Item {
  String itemID;
  String pID;
  String shortDescription;
  String longDescription;
  double cost;
  String zip, state, city;

  public Item(String itemID, String pID, String shortDescription,
                     String longDescription, double cost, String zip, String state, String city) {
	this.pID=pID;
    setItemID(itemID+pID);
    setShortDescription(shortDescription);
    setLongDescription(longDescription);
    setCost(cost);
    this.zip=zip;
    this.city=city;
    this.state=state;
  }
  
  public String getItemCategory(){
	  switch(this.pID.charAt(0)){
				case 's':return "SmartPhone";
				case 't':return "TV";
				case 'l':return "Laptop";
				case 'b':return "Tablet";				
			}
		return "";
  }
  
  public String getPID(){
      return this.pID;
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
    
  public String getItemID() {
    return(itemID);
  }

  public void setItemID(String itemID) {
    this.itemID = itemID;
  }

  public String getShortDescription() {
    return(shortDescription);
  }

  public void setShortDescription(String shortDescription) {
    this.shortDescription = shortDescription;
  }

  public String getLongDescription() {
    return(longDescription);
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public double getCost() {
    return(cost);
  }

  public void setCost(double cost) {
    this.cost = cost;
  }
}