package com.aieme.pleasedheart.repositories;

import com.aieme.pleasedheart.models.Owner;
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
public class OwnerDaoImpl implements OwnerRepository{

    @Autowired
    DataSource dataSource;

    @Override
    public int insert(Owner record) {
        Connection conn = dataSource.getConnection();
        String sqlMaxId = "SELECT max(id) from owners";
        String sql = "INSERT INTO owners (id,name,email,phone) Values (?,?,?,?)";
        int ownerId=0;

        try {
            PreparedStatement statement = conn.prepareStatement(sqlMaxId);
            ResultSet rs=statement.executeQuery();
            if (rs.next()) {
               ownerId=rs.getInt(1)+1;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, ownerId);
            statement.setString(2, record.getName());
            statement.setString(3, record.getEmail());
            statement.setString(4, record.getPhone());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
               System.out.println("New owner inserted successfully");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ownerId;
    }

    @Override
    public void update(Owner record) {
        Connection conn = dataSource.getConnection();
        String sql = "UPDATE owners SET name=?, email=?, phone=? WHERE"
                + " id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, record.getName());
            statement.setString(2, record.getEmail());
            statement.setString(3, record.getPhone());
            statement.setInt(4, record.getId());
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
               System.out.println("Owner updated");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Owner> findAll() {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, email, phone FROM owners";

        List<Owner> owners = new ArrayList<Owner>();
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Owner owner = new Owner();
                owner.setId(rs.getInt(1));
                owner.setName(rs.getString(2));
                owner.setEmail(rs.getString(3));
                owner.setPhone(rs.getString(4));
                owners.add(owner);
            }
            System.out.println("All rows retrieved");
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return owners;
    }

    @Override
    public Owner findById(int id) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, email, phone FROM owners WHERE id=?";
        Owner owner = null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                owner = new Owner();
                owner.setId(rs.getInt(1));
                owner.setName(rs.getString(2));
                owner.setEmail(rs.getString(3));
                owner.setPhone(rs.getString(4));
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return owner;
    }

    @Override
    public void deleteAll() {
        Connection conn = dataSource.getConnection();
        String sql = "DELETE FROM owners WHERE id>=0";

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
        String sql = "DELETE FROM owners WHERE id=?";
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
    public boolean exist(Owner record) {
        Connection conn = dataSource.getConnection();
        String sql = "SELECT id, name, email, phone FROM owners WHERE name=?";
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
