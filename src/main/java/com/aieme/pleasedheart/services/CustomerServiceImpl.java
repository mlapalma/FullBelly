package com.aieme.pleasedheart.services;

import com.aieme.pleasedheart.models.Customer;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.aieme.pleasedheart.models.dao.CustomerDao;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    @Override
    public int insert(Customer record) {
        return customerDao.insert(record);
    }

    @Override
    public void update(Customer record) {
        customerDao.update(record);
    }

    @Override
    public List<Customer> findAll() {
        return customerDao.findAll();
    }

    @Override
    public Customer findById(int id) {
        return customerDao.findById(id);
    }

    @Override
    public void delete(int id) {
        customerDao.deleteById(id);
    }

    @Override
    public void deleteAll() {
        customerDao.deleteAll();
    }

    @Override
    public boolean exist(Customer customer) {
        return customerDao.exist(customer);
    }
}
