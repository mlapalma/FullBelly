package com.aieme.pleasedheart.models.repositories;

import com.aieme.pleasedheart.models.Owner;

public interface OwnerRepository extends Repository<Owner>{

    public boolean exist(Owner owner);
}
