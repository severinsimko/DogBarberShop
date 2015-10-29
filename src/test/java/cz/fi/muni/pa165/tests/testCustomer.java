/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.inject.Inject;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dogbarber.dao.CustomerDao;
import cz.fi.muni.pa165.dogbarber.dao.CustomerDaoImpl;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import static org.testng.Assert.assertEquals;
/**
 *
 * @author Martin Penaz
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class testCustomer extends AbstractTransactionalTestNGSpringContextTests {
   
    @Autowired
    private CustomerDao customerDao = new CustomerDaoImpl();
    
    @Test
    public void customerTest(){
        Customer c = new Customer();
        c.setName("Petr");
        c.setSurName("Frajer");
        c.setAdress("Brno");
        c.setPhoneNumber("123456789");
        
        Customer expected = new Customer();
        expected.setName("Adam");
        expected.setName("Petr");
        expected.setSurName("Frajer");
        expected.setAdress("Brno");
        expected.setPhoneNumber("123456789");
        
        customerDao.createCustomer(c);        
        Customer result = customerDao.findById(c.getId());      

        //System.out.println("result: " + result.getName());
        //System.out.println("expected: " + expected.getName());
        assertEquals(expected.getName(), result.getName());
    }
}
