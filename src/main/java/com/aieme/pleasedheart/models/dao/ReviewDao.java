package com.aieme.pleasedheart.models.dao;

import com.aieme.pleasedheart.models.Review;
import java.sql.Date;
import java.util.List;

public interface ReviewDao extends Dao<Review>{

    public List<Review> findByRestaurantId(int restaurantId);
    public List<Review> findByCustomerId(int customerId);
    public List<Review> findByRestaurantIdBetweenDates(int restaurantId, Date startDate, Date endDate);

}
