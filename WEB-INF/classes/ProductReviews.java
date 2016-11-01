public class ProductReviews {
	 String prodName,  prodCategory,  prodPrice,  retailerName,  retailerZip;
     String retailerCity,  retailerState,  productOnSale,  manufacturerName;
     String userName, reviewText,  reviewDate;
     String userAge, userOccupation, userGender, reviewRating;

	public ProductReviews(String prodName, String prodCategory,
			String prodPrice, String retailerName, String retailerZip,
			String retailerCity, String retailerState, String productOnSale,
			String manufacturerName, String userName, String userAge, String userOccupation, String userGender,
			String reviewText, String reviewDate, String reviewRating) {
		this.prodName = prodName;
		this.prodCategory = prodCategory;
		this.prodPrice = prodPrice;
		this.retailerName = retailerName;
		this.retailerZip = retailerZip;
		this.retailerCity = retailerCity;
		this.retailerState = retailerState;
		this.productOnSale = productOnSale;
		this.manufacturerName = manufacturerName;
		this.userName = userName;
		this.reviewText = reviewText;
		this.reviewRating = reviewRating;
		this.reviewDate = reviewDate;
		this.userAge = userAge;
		this.userOccupation = userOccupation;
		this.userGender = userGender;
	}

	public String getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserOccupation() {
 		return userOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		this.userOccupation = userOccupation;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdCategory() {
		return prodCategory;
	}

	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	public String getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(String prodPrice) {
		this.prodPrice = prodPrice;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public void setRetailerName(String retailerName) {
		this.retailerName = retailerName;
	}

	public String getRetailerZip() {
		return retailerZip;
	}

	public void setRetailerZip(String retailerZip) {
		this.retailerZip = retailerZip;
	}

	public String getRetailerCity() {
		return retailerCity;
	}

	public void setRetailerCity(String retailerCity) {
		this.retailerCity = retailerCity;
	}

	public String getRetailerState() {
		return retailerState;
	}

	public void setRetailerState(String retailerState) {
		this.retailerState = retailerState;
	}

	public String getProductOnSale() {
		return productOnSale;
	}

	public void setProductOnSale(String productOnSale) {
		this.productOnSale = productOnSale;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
}