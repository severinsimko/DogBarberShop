package cz.fi.muni.pa165.dogbarber.service.tests;

import cz.fi.muni.pa165.dogbarber.config.ServiceConfiguration;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import org.springframework.test.context.ContextConfiguration;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Pavel Drobek
 */

@ContextConfiguration(classes=ServiceConfiguration.class)
public class DogServiceTest extends AbstractDogServiceTest {
    
    @Override
    protected void prepareMocks() {
        dogService.deleteDog(dog1);
        when(dogDao.getDogByID(dog1.getId())).thenReturn(dog1);
        
        when(serviceDao.findByName("Washing")).thenReturn(services);
        when(serviceDao.findByName("Spare")).thenReturn(new ArrayList<Service>());
    }
    
    @Test
    public void createDogTest(){
        dogService.createDog(dog1);
        verify(dogDao, times(1)).addDog(dog1);
    }
    
    @Test
    public void deleteDogTest(){
        verify(dogDao, times(1)).removeDog(dog1);
    }
    
    @Test
    public void getDogByIDTest(){
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
    
    @Test(expectedExceptions=DogBarberException.class)
    public void subscribeForNonExistingServiceTest(){
        dogService.subscribeDogForAService(dog1, nonExistingService);
    }
    
    @Test(expectedExceptions=DogBarberException.class)
    public void unsubscribeForNonSubscribedServiceTest(){
        dogService.unsubscribeDogForAService(dog3, service);
    }
    
    @Test(expectedExceptions=DogBarberException.class)
    public void unsubscribeForNonexistingServiceTest(){
        dogService.unsubscribeDogForAService(dog1, nonExistingService);
    }
    
   /* public void getTotalPriceTest(){
        when(dogDao.getDogByID(dog4.getId())).thenReturn(dog4);
        BigDecimal price = dogService.getTotalPrice(dog4.getId());
        assertEquals(price, new BigDecimal("20.00"));
    }*/
}
