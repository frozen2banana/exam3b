package ibf2022.batch2.paf.server.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import ibf2022.batch2.paf.server.services.RestaurantService;


@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restauSvc;

	// TODO: Task 2 - request handler
	@GetMapping(path="/api/{cuisine}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getCuisine(@PathVariable String cuisine, Model model) {
        List<String> cuisineData = restauSvc.getCuisines();

		List<Document> cuisineDataDoc = new ArrayList<>();
		for (String cuisine2 : cuisineData) {
    	Document doc = new Document("cuisine", cuisine2);
    	cuisineDataDoc.add(doc);
		}

		List<String> cuisineDataString = new ArrayList<>();
		for (Document doc : cuisineDataDoc) {
    	String cuisine3 = doc.getString("cuisine");
    	cuisineDataString.add(cuisine3.trim());
		}

		List<String> cuisineDataOut = cuisineDataString.stream()
		.map(s -> s.replace("/", "_"))
		.sorted()
		.collect(Collectors.toList());

		model.addAttribute("cuisineDataOut", cuisineDataOut);

        return new ResponseEntity<>(cuisineDataOut, HttpStatus.OK);
	}// returning a JSON array (List<String>)

	
	// TODO: Task 3 - request handler
	@GetMapping(path="/api/restaurants/{cuisine}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getRestaurantsByCuisine(@PathVariable("cuisine") String cuisine, Model model) {
		
		List<String> restauData = restauSvc.getRestaurantsByCuisine(cuisine).stream()
        .map(restaurant -> restaurant.toString()) 
        .collect(Collectors.toList());

		List<Document> restauDataDoc = new ArrayList<>();
		for (String cuisine2 : restauData) {
    	Document doc = new Document("cuisine", cuisine2);
    	restauDataDoc.add(doc);
		}

		List<String> restauDataString = new ArrayList<>();
		for (Document doc : restauDataDoc) {
    	String cuisine3 = doc.getString("cuisine");
    	restauDataString.add(cuisine3.trim());
		}

		List<String> restauDataOut = restauDataString.stream()
		.map(s -> s.replace("_", "/"))
		.sorted()
		.collect(Collectors.toList());

		model.addAttribute("restauDataOut", restauDataOut);

        return new ResponseEntity<>(restauDataOut, HttpStatus.OK);
		
	}


	// TODO: Task 4 - request handler
	

	// TODO: Task 5 - request handler
	@PostMapping(path="/api/restaurant/{comment}", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addComment(@PathVariable("comment") String comment, Model model) {

	// String commentData = restauSvc.ostRestaurantComment(comment)
	
	// model.addAttribute("commentDataOut", commentDataOut);
	
    return new ResponseEntity<>("{}", HttpStatus.CREATED);
	}

}
