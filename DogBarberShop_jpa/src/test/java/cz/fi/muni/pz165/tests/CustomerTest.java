/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pz165.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dogbarber.dao.CustomerDao;
import cz.fi.muni.pa165.dogbarber.dao.CustomerDaoImpl;
import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.dao.DogDaoImpl;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import org.testng.Assert;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeMethod;
/**
 *
 * @author Martin Penaz
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
public class CustomerTest extends AbstractTransactionalTestNGSpringContextTests {
   
    @Autowired
    private CustomerDao customerDao = new CustomerDaoImpl();
    
    @Autowired
    private DogDao dogDao = new DogDaoImpl();
    
    private Customer c1;
    private Customer c2;
    private Dog d1;
    private Dog d2;
    
    @BeforeMethod
    public void iniTest(){
        c1 = new Customer();
        c1.setName("Petr");
        c1.setSurName("Frajer");
        c1.setAdress("Brno");
        c1.setPhoneNumber("123456789");
        
        c2 = new Customer();
        c2.setName("Petr");
        c2.setSurName("Frajer");
        c2.setAdress("Brno");
        c2.setPhoneNumber("123456789");
        
        d1 = new Dog("Rex", "Vlcak", new GregorianCalendar(2013, Calendar.JANUARY, 10), Color.BLACK);
        d1.setCustomer(c1);
        d2 = new Dog("Dasenka", "Pudl", new GregorianCalendar(2013, Calendar.DECEMBER, 10), Color.WHITE);    
    }
    
    @Test
    public void createSuccessTest(){
        customerDao.createCustomer(c1);        
        Customer result = customerDao.findById(c1.getId());
        assertEquals(c1, result);
    }
        
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createNullTest(){
        customerDao.createCustomer(null);
    }
    
    @Test(expectedExceptions = PersistenceException.class)
    public void createNullNameTest(){
        c1.setName(null);
        customerDao.createCustomer(c1);
    }
    
    @Test
    public void findAllTest(){
        customerDao.createCustomer(c1);
        customerDao.createCustomer(c2);
        Assert.assertTrue(customerDao.getAllCustomers().size() == 2);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void deleteNullTest(){
        customerDao.deleteCustomer(null);
    }
    
    @Test
    public void deleteTest(){
        customerDao.createCustomer(c1);
        customerDao.createCustomer(c2);
        customerDao.deleteCustomer(c2);
        Assert.assertTrue(customerDao.getAllCustomers().size() == 1);
        customerDao.deleteCustomer(c1);
        Assert.assertTrue(customerDao.getAllCustomers().isEmpty());
    }
    
    @Test
    public void updateTest(){
        customerDao.createCustomer(c1);
        Customer updated = customerDao.findById(c1.getId());
        updated.setName("Alfred");
        customerDao.updateCustomer(updated);
        Customer result = customerDao.findById(updated.getId());
        Assert.assertEquals(result.getName(), "Alfred");            
    }
    
  /*  @Test
  /*  public void createWithDogsTest(){
        dogDao.addDog(d1);
        dogDao.addDog(d2);
        
        c1.addDog(d1);
        c1.addDog(d2);
        customerDao.createCustomer(c1);
    }*/
    
}