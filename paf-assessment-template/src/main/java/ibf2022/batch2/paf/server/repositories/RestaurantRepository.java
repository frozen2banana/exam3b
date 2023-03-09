package ibf2022.batch2.paf.server.repositories;

import java.util.List;
import java.util.Optional;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.CreateCollectionOptions;

import ibf2022.batch2.paf.server.models.Address;
import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;

@Repository
public class RestaurantRepository {

	@Autowired
	private MongoTemplate mongoTemplate;

	// TODO: Task 2 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.restaurant.find({}, {cuisine: 1})
	public List<String> getCuisines() {
		Query query = new Query();
        query.fields().include("cuisine");
        return mongoTemplate.find(query, String.class, "restaurant");
    }

	// TODO: Task 3 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	//  db.restaurant.find(
	// 	{ "cuisine": cuisine },
	// 	{ "name": 1, "restaurant_id": 1 }
	//  )
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
        Query query = new Query();
        query.addCriteria(Criteria.where("cuisine").is(cuisine));
        query.fields().include("name").include("restaurant_id");
        return mongoTemplate.find(query, Restaurant.class, "restaurants");
    }
	
	// TODO: Task 4 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// db.restaurants.find(
    // { "restaurant_id": id },
    // {   "restaurant_id": 1,
    //     "name": 1,
    //     "cuisine": 1,
    //     "address.building": 1,
    //     "address.street": 1,
    //     "address.zipcode": 1,
    //     "address.borough": 1   }).forEach(function(doc) {
    // doc.address = doc.address.building + ", " + doc.address.street + ", " + doc.address.zipcode + ", " + doc.address.borough;
    // printjson(doc);
    // });
	public Optional<Restaurant> getRestaurantById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("restaurant_id").is(id));
        query.fields().include("restaurant_id").include("name").include("cuisine")
                .include("address.building").include("address.street")
                .include("address.zipcode").include("address.borough");
        Restaurant restaurant = mongoTemplate.findOne(query, Restaurant.class);
        if (restaurant != null) {
            Address address = restaurant.getAddres();
            address.setAddress(address.getBuilding() + ", " + address.getStreet() + ", " +
                    address.getZipcode() + ", " + address.getBorough());
            return Optional.of(restaurant);
        } else {
            return Optional.empty();
        }
    }

	// TODO: Task 5 
	// Do not change the method's signature
	// Write the MongoDB query for this method in the comments below
	// use foodguide;
	// db.createCollection("comments", {
   	// validator: {
    //   $jsonSchema: {
    //      bsonType: "object",
    //      required: ["restaurantId", "name", "rating", "comment"],
    //      properties: {
    //         restaurantId: {
    //            bsonType: "objectId"
    //         },
    //         name: {
    //            bsonType: "string",
    //            minLength: 4
    //         },
    //         rating: {
    //            bsonType: "int",
    //            minimum: 1,
    //            maximum: 5
    //         },
    //         comment: {
    //            bsonType : "string"
    //         }
    //      }
    //   }
   	// }
	// });
	public void insertRestaurantComment(Comment comment) {
		// MongoTemplate mongoDatabase;
		// MongoCollection<Document> comments = mongoDatabase.getCollection("comments");
    	Document commentDoc = new Document()
            .append("restaurantId", comment.getRestaurantId())
            .append("name", comment.getName())
            .append("rating", comment.getRating())
            .append("comment", comment.getComment());
    	// comments.insertOne(commentDoc);
		
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase database = mongoClient.getDatabase("foodguide");
		
		Document validator = new Document()
			.append("$jsonSchema", new Document()
				.append("bsonType", "object")
				.append("required", List.of("restaurantId", "name", "rating", "comment"))
				.append("properties", new Document()
					.append("restaurantId", new Document()
						.append("bsonType", "objectId"))
					.append("name", new Document()
						.append("bsonType", "string")
						.append("minLength", 4))
					.append("rating", new Document()
						.append("bsonType", "int")
						.append("minimum", 1)
						.append("maximum", 5))
					.append("comment", new Document()
						.append("bsonType" , "string"))));
		
		database.createCollection(
			"comments",
			new CreateCollectionOptions()
		);
	}


}
