import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;
import java.util.*;

public class CreateMongoDBEntities {
    MongoCollection<BasicDBObject> productReviews;
	MongoClient mongoClient;
    MongoDatabase db;

    public void getConnection(){
        mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("BestDealDatabase");
        productReviews = db.getCollection("ProductReviews", BasicDBObject.class);
    }
    
    public void insertReview(ProductReviews prw){
        this.getConnection();
        BasicDBObject doc = new BasicDBObject("fields", "values");
        doc.append("prodName", prw.getProdName());
        doc.append("prodCategory", prw.getProdCategory());
        doc.append("prodPrice", prw.getProdPrice());
        doc.append("retailerName", prw.getRetailerName());
        doc.append("retailerZip", prw.getRetailerZip());
        doc.append("retailerCity", prw.getRetailerCity());
        doc.append("retailerState", prw.getRetailerState());        
        doc.append("productOnSale", prw.getProductOnSale());        
        doc.append("manufacturerName", prw.getManufacturerName());
        doc.append("userName", prw.getUserName());
        doc.append("userAge", prw.getUserAge());
        doc.append("userGender", prw.getUserGender());
        doc.append("userOccupation", prw.getUserOccupation());
        doc.append("reviewRating", prw.getReviewRating());
        doc.append("reviewDate", prw.getReviewDate());
        doc.append("reviewText", prw.getReviewText());
        this.productReviews.insertOne(doc);
    }
    
    public List<ProductReviews> getReviews(String prodName){
        getConnection();
        List<ProductReviews> listReview=new ArrayList<ProductReviews>();
        BasicDBObject query = new BasicDBObject();
        query.put("prodName", prodName);
        List<BasicDBObject> foundDocument = productReviews.find(query).into(new ArrayList<BasicDBObject>());
        for(BasicDBObject obj: foundDocument){
            ProductReviews review=new ProductReviews(obj.getString("prodName"), obj.getString("prodCategory"), obj.getString("prodPrice"), obj.getString("retailerName"), obj.getString("retailerZip"),
			obj.getString("retailerCity"), obj.getString("retailerState"), obj.getString("productOnSale"), obj.getString("manufacturerName"), obj.getString("userName"), obj.getString("userAge"), 
            obj.getString("userOccupation"), obj.getString("userGender"), obj.getString("reviewText"), obj.getString("reviewDate"), obj.getString("reviewRating"));
            listReview.add(review);
        }
        return listReview;
    }
    
    public MultiMap mostLikedProducts(){
        getConnection();
        BasicDBObject query = new BasicDBObject();
        BasicDBObject sortQuery = new BasicDBObject();
        sortQuery.put("reviewRating", -1);
        List<BasicDBObject> foundDocument = productReviews.find(query).sort(sortQuery).into(new ArrayList<BasicDBObject>());
        MultiMap m = new MultiMap();
        for(BasicDBObject obj: foundDocument){
            String rating=obj.getString("reviewRating");
            if(rating==null)
                rating="0";
            m.put(obj.getString("prodName"), Integer.valueOf(rating));
        }
        return m;
    }
}