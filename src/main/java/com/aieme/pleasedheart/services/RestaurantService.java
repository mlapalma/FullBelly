package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Restaurant;
import java.util.List;

public interface RestaurantService extends Service<Restaurant>{

    public List<Restaurant> findByOwnerId(int ownerId);
    public boolean exist(Restaurant record);
}
