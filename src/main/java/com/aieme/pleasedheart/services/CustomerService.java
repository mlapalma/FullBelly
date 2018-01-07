package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Customer;

public interface CustomerService extends Service<Customer>{

    public boolean exist(Customer record);
}
