package com.aieme.pleasedheart.models.dao;

import com.aieme.pleasedheart.models.Restaurant;
import java.util.List;

public interface RestaurantDao extends Dao<Restaurant>{

    public List<Restaurant> findByOwnerId(int ownerId);
    public boolean exist(Restaurant restaurant);
}
