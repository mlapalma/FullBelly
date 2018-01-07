package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Restaurant;
import org.springframework.stereotype.Service;
import com.aieme.pleasedheart.models.dao.RestaurantDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantDao restaurantDao;

    @Override
    public int insert(Restaurant record) {
        return restaurantDao.insert(record);
    }

    @Override
    public void update(Restaurant record) {
        restaurantDao.update(record);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantDao.findAll();
    }

    @Override
    public Restaurant findById(int id) {
        return restaurantDao.findById(id);
    }

    @Override
    public void delete(int id) {
        restaurantDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        restaurantDao.deleteAll();
    }

    @Override
    public boolean exist(Restaurant record) {
        return restaurantDao.exist(record);
    }

    @Override
    public List<Restaurant> findByOwnerId(int ownerId) {
        return restaurantDao.findByOwnerId(ownerId);
    }

}
