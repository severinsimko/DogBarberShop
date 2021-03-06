/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.CustomerDao;
import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import cz.fi.muni.pa165.dogbarber.service.CustomerServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author Martin Penaz
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class CustomerServiceTest {
    @Mock
    CustomerDao customerDao;
    
    @Mock
    DogDao dogDao;
    
    @Autowired
    @InjectMocks
    CustomerServiceImpl customerService;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Customer c1;
    private Customer c2;
    
    private Dog d1;
    private Dog d2;
    
    @BeforeMethod
    public void initCustomers(){
        c1 = new Customer();
        c1.setName("Petr");
        c1.setSurname("Hruska");
        c1.setAdress("Brno");
        c1.setPhoneNumber("123456789");
        c1.setId(new Long(1));
        
        c2 = new Customer();
        c2.setName("Adam");
        c2.setSurname("Svestka");
        c2.setAdress("Brno");
        c2.setPhoneNumber("123456789");
        
        d1 = new Dog("Rex", "Vlcak", new GregorianCalendar(2013, Calendar.JANUARY, 10), Color.BLACK);
        c1.addDog(d1);
        d2 = new Dog("Dasenka", "Pudl", new GregorianCalendar(2013, Calendar.DECEMBER, 10), Color.WHITE);  
    }
    
    @Test
    public void registerCustomerTest(){
        customerService.registerCustomer(c1, "heslo");
        verify(customerDao, times(1)).createCustomer(c1);
    }
    
    @Test
    public void deteleCustomerTest(){
        customerService.deleteCustomer(c1);
        verify(customerDao, times(1)).deleteCustomer(c1);
    }
    
    @Test
    public void findCustomerByIdTest(){
        when(customerDao.findById(c1.getId())).thenReturn(c1);
        assertEquals(customerService.findById(c1.getId()), c1);
    }
    
    @Test
    public void findAllCustomersTest(){
        List<Customer> customers = new ArrayList<>();
        customers.add(c1);
        customers.add(c2);
        
        when(customerDao.getAllCustomers()).thenReturn(customers);
        assertEquals(customerService.findAll(), customers);
        verify(customerDao, times(1)).getAllCustomers();
    }
    
    @Test
    public void addDogTest(){
        customerService.addDog(d2, c1);
        assertTrue(c1.getAllDogs().contains(d1));
        verify(customerDao, times(1)).updateCustomer(c1); 
    }
    
    @Test
    public void removeDogTest(){
        customerService.removeDog(d1, c1);
        assertFalse(c1.getAllDogs().contains(d1));
        verify(customerDao, times(2)).updateCustomer(c1); 
    }
    
    @Test(expectedExceptions=DogBarberException.class)
    public void addAlreadyOwnedDog(){
        customerService.addDog(d1, c1);
    }
    
    @Test(expectedExceptions=DogBarberException.class)
    public void deleteNonExistentDog(){
        customerService.removeDog(d2, c1);
    }    
}
