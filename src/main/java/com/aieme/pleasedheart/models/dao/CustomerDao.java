package com.aieme.pleasedheart.models.dao;

import com.aieme.pleasedheart.models.Customer;

public interface CustomerDao extends Dao<Customer>{

    public boolean exist(Customer customer);
}
