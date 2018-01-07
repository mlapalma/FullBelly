package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Restaurant;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.aieme.pleasedheart.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public int insert(Restaurant record) {
        return restaurantRepository.insert(record);
    }

    @Override
    public void update(Restaurant record) {
        restaurantRepository.update(record);
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    @Override
    public Restaurant findById(int id) {
        return restaurantRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        restaurantRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        restaurantRepository.deleteAll();
    }

    @Override
    public boolean exist(Restaurant record) {
        return restaurantRepository.exist(record);
    }

    @Override
    public List<Restaurant> findByOwnerId(int ownerId) {
        return restaurantRepository.findByOwnerId(ownerId);
    }

}
