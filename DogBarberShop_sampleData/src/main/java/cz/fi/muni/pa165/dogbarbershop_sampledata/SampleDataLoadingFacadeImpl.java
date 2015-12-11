package cz.fi.muni.pa165.dogbarbershop_sampledata;


import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.service.CustomerService;
import cz.fi.muni.pa165.dogbarber.service.DogService;
import cz.fi.muni.pa165.dogbarber.service.EmployeeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import cz.fi.muni.pa165.dogbarber.service.ServiceService;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
/**
 *
 * @author Severin Simko
 */

@Component
@Transactional
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    
    final static Logger logger = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);
    
    @Autowired
    private ServiceService serviceService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private DogService dogService;
    @Autowired
    private EmployeeService employeeService;
   
    @Override
    @SuppressWarnings("unused")
    public void loadData() throws IOException{
        
       Service service= service(90,new BigDecimal(BigInteger.ONE),"Test");
       Service service1= service(120,new BigDecimal(BigInteger.TEN),"Test1");
       Service service2= service(180,new BigDecimal("120"),"Test2");
    
    
    }
    
     private Service service(int lengthInMinutes, BigDecimal price, String name) {
        Service ser = new Service();
        ser.setLengthInMinutes(lengthInMinutes);
        ser.setPrice(price);
        ser.setServiceName(name);
        serviceService.create(ser);
        return ser;
    }
     
     }
    
    
    

