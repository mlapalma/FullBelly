package com.aieme.pleasedheart.models.dao;

import com.aieme.pleasedheart.models.Customer;
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
public class CustomerDaoImpl implements CustomerDao{

    @Autowired
    DataSource dataSource;

    @Override
    public int insert(Customer record) {
        Connection conn = dataSource.getConnection();
        String sqlMaxId = "SELECT max(id) from customers";
        String sql = "INSERT INTO customers (id,name,email,phone) Values (?,?,?,?)";
        int customerId=0;

        try {
            PreparedStatement statement = conn.prepareStatement(sqlMaxId);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
               customerId=rs.getInt(1)+1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.setString(2, record.getName());
            statement.setString(3, record.getEmail());
            statement.setString(4, record.getPhone());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
               System.out.println("New customer inserted successfully");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customerId;
    }

    @Override
    public void update(Customer record) {
        Connection conn = dataSource.getConnection();
        String sql = "UPDATE customers SET name=?, email=?, phone=? WHERE"
                + " id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, record.getName());
            statement.setString(2, record.getEmail());
            statement.setString(3, record.getPhone());
            statement.setInt(4, record.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
               System.out.println("Customer updated");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Customer> findAll() {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, email, phone FROM customers";

        List<Customer> customers = new ArrayList<Customer>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setEmail(rs.getString(3));
                customer.setPhone(rs.getString(4));
                customers.add(customer);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findById(int id) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, email, phone FROM customers WHERE id=?";
        Customer customer = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                customer = new Customer();
                customer.setId(rs.getInt(1));
                customer.setName(rs.getString(2));
                customer.setEmail(rs.getString(3));
                customer.setPhone(rs.getString(4));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    @Override
    public void deleteAll() {
        Connection conn = dataSource.getConnection();
        String sql = "DELETE FROM customers WHERE id>=0";

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
        String sql = "DELETE FROM customers WHERE id=?";
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
    public boolean exist(Customer record) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, email, phone FROM customers WHERE name=?";
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

}
