package com.aieme.pleasedheart.models.dao;

import com.aieme.pleasedheart.models.Restaurant;
import com.aieme.pleasedheart.models.datasources.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantDaoImpl implements RestaurantDao {

    @Autowired
    DataSource dataSource;

    @Autowired
    OwnerDao ownerDao;

    @Override
    public int insert(Restaurant record) {
        Connection conn = dataSource.getConnection();
        String sqlMaxId = "SELECT max(id) from restaurants";
        String sql = "INSERT INTO restaurants (id,name,address,id_owner) Values (?,?,?,?)";
        int restaurantId=0;

        try {
            PreparedStatement statement = conn.prepareStatement(sqlMaxId);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
               restaurantId=rs.getInt(1)+1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, restaurantId);
            statement.setString(2, record.getName());
            statement.setString(3, record.getAddress());
            statement.setInt(4, record.getOwner().getId());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
               System.out.println("New restaurant inserted successfully");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return restaurantId;
    }

    @Override
    public void update(Restaurant record) {
        Connection conn = dataSource.getConnection();
        String sql = "UPDATE restaurants SET name=?, address=?, id_owner=? WHERE"
                + " id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, record.getName());
            statement.setString(2, record.getAddress());
            statement.setInt(3, record.getOwner().getId());
            statement.setInt(4, record.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
               System.out.println("Restaurant updated");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Restaurant> findAll() {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, address, id_owner FROM restaurants";

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt(1));
                restaurant.setName(rs.getString(2));
                restaurant.setAddress(rs.getString(3));
                restaurant.setOwner(ownerDao.findById(rs.getInt(4)));
                restaurants.add(restaurant);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return restaurants;
    }

    @Override
    public Restaurant findById(int id) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, address, id_owner FROM restaurants WHERE id=?";
        String sqlOwner = "SELECT id, name, email, phone FROM owners where id=?";
        Restaurant restaurant = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                restaurant = new Restaurant();
                restaurant.setId(rs.getInt(1));
                restaurant.setName(rs.getString(2));
                restaurant.setAddress(rs.getString(3));
                restaurant.setOwner(ownerDao.findById(rs.getInt(4)));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return restaurant;
    }

    @Override
    public void deleteAll() {
        Connection conn = dataSource.getConnection();
        String sql = "DELETE FROM restaurants WHERE id>=0";

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
        String sql = "DELETE FROM restaurants WHERE id=?";
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
    public boolean exist(Restaurant record) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, address, id_owner FROM restaurants WHERE name=?";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, record.getName());
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                return true;
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Restaurant> findByOwnerId(int ownerId) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, address, id_owner FROM restaurants "
                + "WHERE id_owner=?";

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ownerId);
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                Restaurant restaurant = new Restaurant();
                restaurant.setId(rs.getInt(1));
                restaurant.setName(rs.getString(2));
                restaurant.setAddress(rs.getString(3));
                restaurant.setOwner(ownerDao.findById(rs.getInt(4)));
                restaurants.add(restaurant);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return restaurants;
    }
}