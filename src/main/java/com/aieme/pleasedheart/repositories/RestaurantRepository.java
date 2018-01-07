package com.aieme.pleasedheart.repositories;

import com.aieme.pleasedheart.models.Restaurant;
import java.util.List;

public interface RestaurantRepository extends Repository<Restaurant>{

    public List<Restaurant> findByOwnerId(int ownerId);
    public boolean exist(Restaurant restaurant);
}
