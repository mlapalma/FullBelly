package com.aieme.pleasedheart.repositories;

import com.aieme.pleasedheart.models.Customer;

public interface CustomerRepository extends Repository<Customer>{

    public boolean exist(Customer customer);
}
