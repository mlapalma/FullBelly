package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Review;
import org.springframework.stereotype.Service;
import com.aieme.pleasedheart.models.ReviewAverage;
import com.aieme.pleasedheart.models.dao.ReviewDao;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    @Override
    public int insert(Review record) {
        return reviewDao.insert(record);
    }

    @Override
    public void update(Review record) {
        reviewDao.update(record);
    }

    @Override
    public List<Review> findAll() {
        return reviewDao.findAll();
    }

    @Override
    public Review findById(int id) {
        return reviewDao.findById(id);
    }

    @Override
    public void delete(int id) {
        reviewDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        reviewDao.deleteAll();
    }

    @Override
    public List<Review> findByRestaurantId(int restaurantId) {
        return reviewDao.findByRestaurantId(restaurantId);
    }

    @Override
    public List<Review> findByCustomerId(int customerId) {
        return reviewDao.findByCustomerId(customerId);
    }

    @Override
    public List<Review> findByRestaurantIdBetweenDates(int restaurantId, Date starDate, Date endDate) {
        return reviewDao.findByRestaurantIdBetweenDates(restaurantId, starDate, endDate);
    }

    @Override
    public ReviewAverage calculateTotalReviewAverageByRestaurantId(int restaurantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReviewAverage> calculateRestaurantsTotalReviewAverageByOwnerId(int restaurantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReviewAverage> calculateEachDayReviewAverageByRestaurantIdBetweenDates(int restaurantId, Date startDate, Date endDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReviewAverage> calculateAllDaysReviewAverageByRestaurantId(int restaurantId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ReviewAverage> calculateOneDaysReviewAverageByRestaurantId(int restaurantId, Date day) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
