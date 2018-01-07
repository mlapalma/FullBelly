package com.aieme.pleasedheart.restcontrollers;

import com.aieme.pleasedheart.models.Restaurant;
import com.aieme.pleasedheart.services.RestaurantService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

        @Autowired
    private RestaurantService restaurantService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Restaurant>> listAllRestaurants(){
        List<Restaurant> restaurants = restaurantService.findAll();
        if (restaurants.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Restaurant> getRestaurant(@PathVariable("id") int id){
        Restaurant restaurant = restaurantService.findById(id);
        if(restaurant==null)
            return new ResponseEntity("Unable to get a restaurant by that id",HttpStatus.NOT_FOUND);
        return new ResponseEntity<Restaurant>(restaurant, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant,UriComponentsBuilder ucBuilder){
        if(restaurantService.exist(restaurant))
            return new ResponseEntity("Unable to create restaurant. A restaurant with that name already exists",
                    HttpStatus.CONFLICT);
        int restaurantId = restaurantService.insert(restaurant);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/restaurants/{id}").buildAndExpand(restaurantId).toUri());
        return new ResponseEntity<Restaurant>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable("id") int id, @RequestBody Restaurant restaurant) {
        Restaurant currentRestaurant = restaurantService.findById(id);
        if(currentRestaurant==null)
            return new ResponseEntity("Restaurant does not exists",HttpStatus.NOT_FOUND);

        currentRestaurant.setName(restaurant.getName());
        currentRestaurant.setAddress(restaurant.getAddress());
        currentRestaurant.setOwner(restaurant.getOwner());

        restaurantService.update(currentRestaurant);
        return new ResponseEntity<Restaurant>(currentRestaurant, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Restaurant> deleteRestaurant(@PathVariable("id") int id) {
        Restaurant currentRestaurant = restaurantService.findById(id);
        if(currentRestaurant==null)
            return new ResponseEntity("Restaurant does not exists",HttpStatus.NOT_FOUND);

        restaurantService.delete(id);
        return new ResponseEntity<Restaurant>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/owner", method = RequestMethod.GET)
    public ResponseEntity<List<Restaurant>> listRestaurantsByOwnerId(@RequestParam("id") int ownerId){
        List<Restaurant> restaurants = restaurantService.findByOwnerId(ownerId);
        if(restaurants.isEmpty())
            return new ResponseEntity("The owner specified does not have a restaurant",HttpStatus.NOT_FOUND);
        return new ResponseEntity<List<Restaurant>>(restaurants, HttpStatus.OK);
    }

}
