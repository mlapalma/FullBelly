package com.aieme.pleasedheart.operations;

import com.aieme.pleasedheart.configuration.BaseConfiguration;
import com.aieme.pleasedheart.models.Customer;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import com.aieme.pleasedheart.models.repositories.CustomerRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@Import(BaseConfiguration.class)
public class CustomerRepositoryIntegrationTests {

    @Autowired
    ApplicationContext appContext;
    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void checkDependencyBeans(){
        CustomerRepository customerDaoBean = (CustomerRepository)appContext.getBean("customerRepositoryImpl");
        assertNotNull(customerDaoBean);
    }

    @Test
    public void customerDaoShouldInsertDemoCustomer(){
        //Assign
        Customer demoCustomer = new Customer();
        demoCustomer.setName("John Doe");
        demoCustomer.setEmail("jhon.doe@gmail.com");
        demoCustomer.setPhone("1111111111");

        //Act
        customerRepository.insert(demoCustomer);

        //Assert
        assertTrue(customerRepository.exist(demoCustomer));

        //Clean
        customerRepository.deleteById(demoCustomer.getId());
    }
}
