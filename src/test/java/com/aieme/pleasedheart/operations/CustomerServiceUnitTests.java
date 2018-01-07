package com.aieme.pleasedheart.operations;

import com.aieme.pleasedheart.configuration.BaseConfiguration;
import com.aieme.pleasedheart.models.Customer;
import com.aieme.pleasedheart.services.CustomerServiceImpl;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.aieme.pleasedheart.models.dao.CustomerDao;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Import(BaseConfiguration.class)
public class CustomerServiceUnitTests {

    @Mock
    CustomerDao customerDao;

    @InjectMocks
    CustomerServiceImpl customerService;

    @Test
    public void customerServiceShouldNotBeNull(){
        assertNotNull(customerService);
    }

    @Test
    public void customerServiceRetrieveClientById(){
    //Mock Pojo
    Customer customer = new Customer();
    customer.setId(1);
    customer.setName("John Doe");
    customer.setEmail("john.doe@gmail.com");
    customer.setPhone("1111111111");

    //Mock Service Operation
    when(customerDao.findById(1)).thenReturn(customer);

    //Assert
    assertEquals("John Doe", customerDao.findById(1).getName());

    }

}
