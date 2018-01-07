package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Review;
import com.aieme.pleasedheart.models.ReviewAverage;
import java.sql.Date;
import java.util.List;

public interface ReviewService extends Service<Review>{

    public List<Review> findByRestaurantId(int restaurantId);
    public List<Review> findByCustomerId(int customerId);
    public List<Review> findByRestaurantIdBetweenDates(int restaurantId, Date starDate, Date endDate);
    public ReviewAverage calculateTotalReviewAverageByRestaurantId(int restaurantId);
    public List<ReviewAverage> calculateRestaurantsTotalReviewAverageByOwnerId(int restaurantId);
    public List<ReviewAverage> calculateEachDayReviewAverageByRestaurantIdBetweenDates(int restaurantId,Date startDate, Date endDate);
    public List<ReviewAverage> calculateAllDaysReviewAverageByRestaurantId(int restaurantId);
    public List<ReviewAverage> calculateOneDaysReviewAverageByRestaurantId(int restaurantId, Date day);
}
