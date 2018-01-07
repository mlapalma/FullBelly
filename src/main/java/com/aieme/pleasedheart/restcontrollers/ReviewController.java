package com.aieme.pleasedheart.restcontrollers;

import com.aieme.pleasedheart.models.Review;
import com.aieme.pleasedheart.services.ReviewService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Review>> listAllReviews(){
        List<Review> reviews = reviewService.findAll();
        if (reviews.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Review> getReview(@PathVariable("id") int id){
        Review review = reviewService.findById(id);
        if(review==null)
            return new ResponseEntity("Unable to get a review by that id",HttpStatus.NOT_FOUND);
        return new ResponseEntity<Review>(review, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<Review> createReview(@RequestBody Review review,UriComponentsBuilder ucBuilder){
        int reviewId = reviewService.insert(review);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/reviews/{id}").buildAndExpand(reviewId).toUri());
        return new ResponseEntity<Review>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Review> updateReview(@PathVariable("id") int id, @RequestBody Review review) {
        Review currentReview = reviewService.findById(id);
        if(currentReview==null)
            return new ResponseEntity("Review does not exists",HttpStatus.NOT_FOUND);

        currentReview.setDate(review.getDate());
        currentReview.setCustomer(review.getCustomer());
        currentReview.setRestaurant(review.getRestaurant());
        currentReview.setScoreService(review.getScoreService());
        currentReview.setScoreFood(review.getScoreFood());
        currentReview.setScoreEnvironment(review.getScoreEnvironment());

        reviewService.update(currentReview);
        return new ResponseEntity<Review>(currentReview, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Review> deleteReview(@PathVariable("id") int id) {
        Review currentReview = reviewService.findById(id);
        if(currentReview==null)
            return new ResponseEntity("Review does not exists",HttpStatus.NOT_FOUND);

        reviewService.delete(id);
        return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
    }

}
