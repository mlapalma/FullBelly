package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Owner;

public interface OwnerService extends Service<Owner>{

    public boolean exist(Owner record);
}
