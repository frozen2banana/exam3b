package ibf2022.batch2.paf.server.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ibf2022.batch2.paf.server.models.Comment;
import ibf2022.batch2.paf.server.models.Restaurant;
import ibf2022.batch2.paf.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

	@Autowired RestaurantRepository restauRepo;

	private Logger log = Logger.getLogger(RestaurantService.class.getName());

	// TODO: Task 2
	// Do not change the method's signature
	public List<String> getCuisines() {
        log.info("Pass-Through Service: getCuisines()");
        return restauRepo.getCuisines();
    }

	// TODO: Task 3 
	// Do not change the method's signature
	public List<Restaurant> getRestaurantsByCuisine(String cuisine) {
		log.info("Pass-Through Service: getRestaurantsByCuisine()");
		return restauRepo.getRestaurantsByCuisine(cuisine);
	}

	// TODO: Task 4 
	// Do not change the method's signature
	public Optional<Restaurant> getRestaurantById(String id) {
		log.info("Pass-Through Service: getRestaurantById()");
		return restauRepo.getRestaurantById(id);
	}

	// TODO: Task 5 
	// Do not change the method's signature
	public void postRestaurantComment(Comment comment) {
		log.info("Pass-Through Service: postRestaurantComment()");
		// return restauRepo.insertRestaurantComment(comment)
	}
}
