package com.aieme.pleasedheart.models.repositories;

import com.aieme.pleasedheart.models.Review;
import com.aieme.pleasedheart.models.datasources.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewRepositoryImpl implements ReviewRepository {

    @Autowired
    DataSource dataSource;

    @Autowired
    CustomerRepository customerDao;

    @Autowired
    RestaurantRepository restaurantDao;

    @Override
    public int insert(Review record) {
        Connection conn = dataSource.getConnection();
        String sqlMaxId = "SELECT max(id) from reviews";
        String sql = "INSERT INTO reviews (id,date,id_customer,id_restaurant,score_service,score_food,score_environment) Values (?,?,?,?,?,?,?)";
        int reviewId=0;

        try {
            PreparedStatement statement = conn.prepareStatement(sqlMaxId);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
               reviewId=rs.getInt(1)+1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, reviewId);
            statement.setDate(2, record.getDate());
            statement.setInt(3, record.getCustomer().getId());
            statement.setInt(4, record.getRestaurant().getId());
            statement.setInt(5, record.getScoreService());
            statement.setInt(6, record.getScoreFood());
            statement.setInt(7, record.getScoreEnvironment());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
               System.out.println("New review inserted successfully");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reviewId;
    }

    @Override
    public void update(Review record) {
        Connection conn = dataSource.getConnection();
        String sql = "UPDATE reviews SET date=?, id_customer=?, id_restaurant=?, "
                + "score_service=?, score_food=?, score_environment=? WHERE id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setDate(1, record.getDate());
            statement.setInt(2, record.getCustomer().getId());
            statement.setInt(3, record.getRestaurant().getId());
            statement.setInt(4, record.getScoreService());
            statement.setInt(5, record.getScoreFood());
            statement.setInt(6, record.getScoreEnvironment());
            statement.setInt(7, record.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
               System.out.println("Review updated");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Review> findAll() {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, date, id_customer, id_restaurant, score_service, score_food, score_environment FROM reviews";

        List<Review> reviews = new ArrayList<Review>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setDate(rs.getDate(2));
                review.setCustomer(customerDao.findById(rs.getInt(3)));
                review.setRestaurant(restaurantDao.findById(rs.getInt(4)));
                review.setScoreService(rs.getInt(5));
                review.setScoreFood(rs.getInt(6));
                review.setScoreEnvironment(rs.getInt(7));
                reviews.add(review);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Review findById(int id) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, date, id_customer, id_restaurant, score_service, score_food, score_environment FROM reviews WHERE id=?";
        Review review = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                review = new Review();
                review.setId(rs.getInt(1));
                review.setDate(rs.getDate(2));
                review.setCustomer(customerDao.findById(rs.getInt(3)));
                review.setRestaurant(restaurantDao.findById(rs.getInt(4)));
                review.setScoreService(rs.getInt(5));
                review.setScoreFood(rs.getInt(6));
                review.setScoreEnvironment(rs.getInt(7));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return review;
    }

    @Override
    public void deleteAll() {
        Connection conn = dataSource.getConnection();
        String sql = "DELETE FROM reviews WHERE id>=0";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("All rows deleted from table");

            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        Connection conn = dataSource.getConnection();
        String sql = "DELETE FROM reviews WHERE id=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Row deleted");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Review> findByRestaurantId(int restaurantId) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, date, id_customer, id_restaurant, score_service, score_food, "
                + "score_environment FROM reviews WHERE id_restaurant=?";

        List<Review> reviews = new ArrayList<Review>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setDate(rs.getDate(2));
                review.setCustomer(customerDao.findById(rs.getInt(3)));
                review.setRestaurant(restaurantDao.findById(rs.getInt(4)));
                review.setScoreService(rs.getInt(5));
                review.setScoreFood(rs.getInt(6));
                review.setScoreEnvironment(rs.getInt(7));
                reviews.add(review);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<Review> findByCustomerId(int customerId) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, date, id_customer, id_restaurant, score_service, score_food, "
                + "score_environment FROM reviews WHERE id_customer=?";

        List<Review> reviews = new ArrayList<Review>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setDate(rs.getDate(2));
                review.setCustomer(customerDao.findById(rs.getInt(3)));
                review.setRestaurant(restaurantDao.findById(rs.getInt(4)));
                review.setScoreService(rs.getInt(5));
                review.setScoreFood(rs.getInt(6));
                review.setScoreEnvironment(rs.getInt(7));
                reviews.add(review);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reviews;
    }

    @Override
    public List<Review> findByRestaurantIdBetweenDates(int restaurantId, Date startDate, Date endDate) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, date, id_customer, id_restaurant, score_service, score_food, "
                + "score_environment FROM reviews WHERE id_restaurant=? AND "
                + "date>=? AND date<=?";

        List<Review> reviews = new ArrayList<Review>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setDate(2, startDate);
            statement.setDate(3, endDate);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Review review = new Review();
                review.setId(rs.getInt(1));
                review.setDate(rs.getDate(2));
                review.setCustomer(customerDao.findById(rs.getInt(3)));
                review.setRestaurant(restaurantDao.findById(rs.getInt(4)));
                review.setScoreService(rs.getInt(5));
                review.setScoreFood(rs.getInt(6));
                review.setScoreEnvironment(rs.getInt(7));
                reviews.add(review);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return reviews;
    }

}
