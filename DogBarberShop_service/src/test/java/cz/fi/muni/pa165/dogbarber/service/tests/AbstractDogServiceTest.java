package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.service.DogServiceImpl;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.BeforeClass;

/**
 *
 * @author Pavel Drobek
 */
public abstract class AbstractDogServiceTest {
    @Mock
    protected DogDao dogDao;
    
    @Mock
    protected ServiceDao serviceDao;
    
    @Autowired
    @InjectMocks
    protected DogServiceImpl dogService;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
        
        prepareMocks();
    }
    
    protected abstract void prepareMocks();
    
    protected Dog dog1;
    protected Dog dog2;
    protected Dog dog3;
    protected Dog dog4;
    
    protected Service service;
    protected Service service1;
    protected Service nonExistingService;
    
    protected List<Service> services;
    
    @BeforeClass
    public void prepareData(){
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2011, 9, 20);
        dog1 = new Dog("Johny The Dog", "Husky", bornDate, Color.WHITE);
        dog2 = new Dog("Johny's Twin Bulgod", "Buldog", bornDate, Color.BLACK);
        dog3 = new Dog("Doge", "Chivauva", bornDate, Color.BROWN);
        dog4 = new Dog("Johny The Dog", "Husky", bornDate, Color.WHITE);
        
        service = new Service();
        service.setServiceName("Washing");
        service.setPrice(new BigDecimal("10.00"));
        
        service1 = new Service();
        service1.setServiceName("Skinning");
        service1.setPrice(new BigDecimal("20.00"));
        
        
        services = new ArrayList<>();
        services.add(service);
        
        nonExistingService = new Service();
        nonExistingService.setServiceName("Spare");
        
        dog2.addService(service);
        
        dog4.addService(service);
        dog4.addService(service1);
    }
}
