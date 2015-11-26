package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author MichalBrath
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class EmployeeServiceTest extends AbstractTransactionalTestNGSpringContextTests {
/*
    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    @InjectMocks
    private EmployeeService employeeServ;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Employee emp;

    @BeforeMethod
    public void setEmp() {

        emp = new Employee();
    }

    @Test
    public void testUpdateExcusion() {

        employeeDao.addEmployee(emp);
        emp.setName("new");
        //employeeServ.update(emp);
    }*/
    @Test
    public void a(){};
}