package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Restaurant;
import com.aieme.pleasedheart.models.Review;
import org.springframework.stereotype.Service;
import com.aieme.pleasedheart.models.ReviewAverage;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.springframework.beans.factory.annotation.Autowired;
import com.aieme.pleasedheart.repositories.RestaurantRepository;
import com.aieme.pleasedheart.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public int insert(Review record) {
        return reviewRepository.insert(record);
    }

    @Override
    public void update(Review record) {
        reviewRepository.update(record);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public Review findById(int id) {
        return reviewRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reviewRepository.deleteAll();
    }

    @Override
    public List<Review> findByRestaurantId(int restaurantId) {
        return reviewRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Review> findByCustomerId(int customerId) {
        return reviewRepository.findByCustomerId(customerId);
    }

    @Override
    public List<Review> findByRestaurantIdBetweenDates(int restaurantId, Date starDate, Date endDate) {
        return reviewRepository.findByRestaurantIdBetweenDates(restaurantId, starDate, endDate);
    }

    @Override
    public ReviewAverage calculateTotalReviewAverageByRestaurantId(int restaurantId) {
        ReviewAverage reviewAverage = new ReviewAverage();
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        int scoreServiceSum=0;
        int scoreFoodSum=0;
        int scoreEnvironmentSum=0;
        float scoreServiceAvg=0;
        float scoreFoodAvg=0;
        float scoreEnvironmentAvg=0;
        Date minimumDate = new Date(Long.MAX_VALUE);
        Date maximumDate = new Date(Long.MIN_VALUE);
        for(Review review:reviews){
            scoreServiceSum+=review.getScoreService();
            scoreFoodSum+=review.getScoreFood();
            scoreEnvironmentSum+=review.getScoreEnvironment();
            if(review.getDate().before(minimumDate))
                minimumDate=review.getDate();
            if(review.getDate().after(maximumDate))
                maximumDate=review.getDate();
        }
        if(reviews.size()>0){
            scoreServiceAvg=scoreServiceSum/(float)reviews.size();
            scoreFoodAvg=scoreFoodSum/(float)reviews.size();
            scoreEnvironmentAvg=scoreEnvironmentSum/(float)reviews.size();
        }
        reviewAverage.setStartDate(minimumDate);
        reviewAverage.setEndDate(maximumDate);
        reviewAverage.setRestaurant(restaurantRepository.findById(restaurantId));
        reviewAverage.setAvgScoreService(scoreServiceAvg);
        reviewAverage.setAvgScoreFood(scoreFoodAvg);
        reviewAverage.setAvgScoreEnvironment(scoreEnvironmentAvg);

        return reviewAverage;
    }

    @Override
    public List<ReviewAverage> calculateRestaurantsTotalReviewAverageByOwnerId(int ownerId) {
        List<ReviewAverage> reviewAvgs = new ArrayList<ReviewAverage>();
        for(Restaurant restaurant:restaurantRepository.findByOwnerId(ownerId)){
            reviewAvgs.add(calculateTotalReviewAverageByRestaurantId(restaurant.getId()));
        }
        return reviewAvgs;
    }

    @Override
    public ReviewAverage calculateReviewAverageByRestaurantIdBetweenDates(int restaurantId, Date startDate, Date endDate) {
        ReviewAverage reviewAverage = new ReviewAverage();
        List<Review> reviews = reviewRepository.findByRestaurantIdBetweenDates(restaurantId,startDate,endDate);
        int scoreServiceSum=0;
        int scoreFoodSum=0;
        int scoreEnvironmentSum=0;
        float scoreServiceAvg=0;
        float scoreFoodAvg=0;
        float scoreEnvironmentAvg=0;
        for(Review review:reviews){
            scoreServiceSum+=review.getScoreService();
            scoreFoodSum+=review.getScoreFood();
            scoreEnvironmentSum+=review.getScoreEnvironment();
        }
        if(reviews.size()>0){
            scoreServiceAvg=scoreServiceSum/(float)reviews.size();
            scoreFoodAvg=scoreFoodSum/(float)reviews.size();
            scoreEnvironmentAvg=scoreEnvironmentSum/(float)reviews.size();
        }
        reviewAverage.setStartDate(startDate);
        reviewAverage.setEndDate(endDate);
        reviewAverage.setRestaurant(restaurantRepository.findById(restaurantId));
        reviewAverage.setAvgScoreService(scoreServiceAvg);
        reviewAverage.setAvgScoreFood(scoreFoodAvg);
        reviewAverage.setAvgScoreEnvironment(scoreEnvironmentAvg);

        return reviewAverage;
    }


    @Override
    public List<ReviewAverage> calculateEachDayReviewAverageByRestaurantIdBetweenDates(int restaurantId, Date startDate, Date endDate) {
        List<ReviewAverage> reviewAvgs=new ArrayList<ReviewAverage>();
        DateTime start = new DateTime(startDate.getTime());
        DateTime end = new DateTime(endDate.getTime());
        int days=0;
        if(end.compareTo(start)>0)
            days = Days.daysBetween(start, end).getDays();
        for (int i=0; i < days; i++) {
            Date st = new Date(start.withFieldAdded(DurationFieldType.days(), i).getMillis());
            Date et = new Date(start.withFieldAdded(DurationFieldType.days(), i+1).getMillis());
            reviewAvgs.add(calculateReviewAverageByRestaurantIdBetweenDates(restaurantId,st,et));
        }
        return reviewAvgs;
    }

    @Override
    public List<ReviewAverage> calculateAllDaysReviewAverageByRestaurantId(int restaurantId) {
        List<ReviewAverage> reviewAvgs = new ArrayList<ReviewAverage>();
        List<Review> reviews = reviewRepository.findByRestaurantId(restaurantId);
        Date minimumDate = new Date(Long.MAX_VALUE);
        Date maximumDate = new Date(Long.MIN_VALUE);
        for(Review review:reviews){
            if(review.getDate().before(minimumDate))
                minimumDate=review.getDate();
            if(review.getDate().after(maximumDate))
                maximumDate=review.getDate();
        }
        if(reviews.size()>0){
            reviewAvgs = calculateEachDayReviewAverageByRestaurantIdBetweenDates(restaurantId,minimumDate,maximumDate);
        }
        return reviewAvgs;
    }

}
