package cz.fi.muni.pa165.dogbarber.service.tests;

import javax.persistence.PersistenceException;
import static org.mockito.Mockito.doThrow;
import org.springframework.dao.DataAccessException;
import org.testng.annotations.Test;

/**
 *
 * @author Pavel Drobe
 */
public class DogServiceFailingPersistenceTest extends AbstractDogServiceTest {
    @Override
    protected void prepareMocks() {
        doThrow(new PersistenceException()).when(dogDao).addDog(dog1);
        doThrow(new PersistenceException()).when(dogDao).removeDog(dog1);
        doThrow(new PersistenceException()).when(dogDao).getDogByID(dog1.getId());
        doThrow(new PersistenceException()).when(dogDao).getAllDogs();
        doThrow(new PersistenceException()).when(dogDao).updateDog(dog1);
        doThrow(new PersistenceException()).when(serviceDao).findByName(service.getServiceName());
        doThrow(new PersistenceException()).when(dogDao).updateDog(dog1);
        doThrow(new PersistenceException()).when(serviceDao).findByName(service.getServiceName());
    }
    
    @Test(expectedExceptions=DataAccessException.class)
    public void createDogFailTest(){
        dogService.createDog(dog1);
    }
    
    @Test(expectedExceptions=DataAccessException.class)
    public void deleteDogFailTest(){
        dogService.deleteDog(dog1);
    }
    
    @Test(expectedExceptions=DataAccessException.class)
    public void getByIdDogFailTest(){
        dogService.getDogByID(dog1.getId());
    }
    
    @Test(expectedExceptions=DataAccessException.class)
    public void getAllDogsFailTest(){
        dogService.getAllDogs();
    }
    
    @Test(expectedExceptions=DataAccessException.class)
    public void subscribeDogFailTest(){
        dogService.subscribeDogForAService(dog1, service);
    }
    
    @Test(expectedExceptions=DataAccessException.class)
    public void unsubscribeDogFailTest(){
        dogService.unsubscribeDogForAService(dog2, service);
    }
}
