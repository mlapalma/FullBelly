package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Customer;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.aieme.pleasedheart.repositories.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public int insert(Customer record) {
        return customerRepository.insert(record);
    }

    @Override
    public void update(Customer record) {
        customerRepository.update(record);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerRepository.findById(id);
    }

    @Override
    public void delete(int id) {
        customerRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();
    }

    @Override
    public boolean exist(Customer customer) {
        return customerRepository.exist(customer);
    }
}
