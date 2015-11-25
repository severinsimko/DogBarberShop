package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.service.DogServiceImpl;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
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
 * @author Pavel Drobek
 */

@ContextConfiguration(classes=ServiceConfiguration.class)
public class DogServiceTest {
    
    @Mock
    DogDao dogDao;
    
    @Autowired
    @InjectMocks
    private DogServiceImpl dogService;
    
    @BeforeClass
    public void setup() throws ServiceException
    {
        MockitoAnnotations.initMocks(this);
    }
    
    private Dog dog1;
    private Dog dog2;
    
    private Service service;
    
    @BeforeMethod
    public void prepareDog(){
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2011, 9, 20);
        dog1 = new Dog("Johny The Dog", "Husky", bornDate, Color.WHITE);
        
        bornDate.set(2011, 9, 20);
        dog2 = new Dog("Johny's Twin Bulgod", "Buldog", bornDate, Color.BLACK);
        
        service = new Service();
        service.setServiceName("Washing");
        
        dog2.addService(service);
    }
    
    @Test
    public void createDogTest(){
        dogService.createDog(dog1);
        verify(dogDao, times(1)).addDog(dog1);
    }
        
    
    @Test
    public void deleteDogTest(){
        dogService.deleteDog(dog1);
        verify(dogDao, times(1)).removeDog(dog1);
    }
    
    @Test
    public void getDogByIDTest(){
        when(dogDao.getDogByID(dog1.getId())).thenReturn(dog1);
        assertEquals(dogService.getDogByID(dog1.getId()), dog1);
    }
    
    @Test
    public void getAllDogsTest(){
        List<Dog> dogs = new ArrayList<>();
        dogs.add(dog1);
        dogs.add(dog2);
        
        when(dogDao.getAllDogs()).thenReturn(dogs);
        
        assertEquals(dogService.getAllDogs(), dogs);
        verify(dogDao, times(1)).getAllDogs();
    }
    
    @Test
    public void subscribeDogForAServiceTest(){
        dogService.subscribeDogForAService(dog1, service);
        assertTrue(dog1.getServices().contains(service));
        verify(dogDao, times(1)).updateDog(dog1);
    }
    
    @Test
    public void unsubscribeDogForAServiceTest(){
        dogService.unsubscribeDogForAService(dog2, service);
        assertFalse(dog2.getServices().contains(service));
        verify(dogDao, times(1)).updateDog(dog2);
    }
}
