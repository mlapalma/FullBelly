package com.aieme.pleasedheart.models.dao;

import com.aieme.pleasedheart.models.Owner;

public interface OwnerDao extends Dao<Owner>{

    public boolean exist(Owner owner);
}
