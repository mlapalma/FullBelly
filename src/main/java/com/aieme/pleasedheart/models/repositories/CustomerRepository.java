package com.aieme.pleasedheart.models.repositories;

import com.aieme.pleasedheart.models.Customer;

public interface CustomerRepository extends Repository<Customer>{

    public boolean exist(Customer customer);
}
