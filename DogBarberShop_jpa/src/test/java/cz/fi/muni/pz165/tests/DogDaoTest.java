package cz.fi.muni.pz165.tests;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.entity.Customer;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

/**
 *
 * @author Pavel Drobek
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DogDaoTest extends AbstractTransactionalTestNGSpringContextTests {

    @Autowired
    public DogDao dogDao;
    
    @Test
    public void dogAddTest(){
        Dog epicDogCopy;
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2001, 5, 3);
        
        Dog epicDog = new Dog("Alik", "Chivavua", bornDate, Color.BLACK);
        dogDao.addDog(epicDog);
        
        epicDogCopy = dogDao.getDogByID(epicDog.getId());
        assertEquals(epicDog.getName(), epicDogCopy.getName());
        assertEquals(epicDog.getAge(), epicDogCopy.getAge());
        assertEquals(epicDog.getBreed(), epicDogCopy.getBreed());
        assertEquals(epicDog.getColor(), epicDogCopy.getColor());
        assertEquals(epicDog.getId(), epicDogCopy.getId());
    }
    
    @Test
    public void dogRemoveTest(){
        Dog epicDogCopy;
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2001, 5, 3);
        
        Dog epicDog = new Dog("Alik", "Chivavua", bornDate, Color.BLACK);
        dogDao.addDog(epicDog);
        dogDao.removeDog(epicDog);
        
        epicDogCopy = dogDao.getDogByID(epicDog.getId());
        assertNull(epicDogCopy);
    }
    
    @Test
    public void dogGetAllDogsTest(){
        Dog dog1, dog2, dog3;
        
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2001, 5, 3);
        
        dog1 = new Dog("Alik", "Chivavua", bornDate, Color.BROWN);
        dog2 = new Dog("Smolik", "Unspecified", bornDate, Color.BLACK);
        dog3 = new Dog("Mungo", "Husky", bornDate, Color.WHITE);
        
        dogDao.addDog(dog1);
        dogDao.addDog(dog2);
        dogDao.addDog(dog3);
        
        List<Dog> dogs = dogDao.getAllDogs();
        assertTrue(dogs.size() == 3);
        
        assertTrue(dogs.contains(dog1));
        assertTrue(dogs.contains(dog2));
        assertTrue(dogs.contains(dog3));
    }
    
    @Test
    public void updateDogTest(){
        Dog epicDogCopy;
        Calendar bornDate = Calendar.getInstance();
        bornDate.set(2001, 5, 3);
        
        Dog epicDog = new Dog("Alik", "Chivavua", bornDate, Color.BLACK);
        dogDao.addDog(epicDog);
        
        Customer dogsCustomer = new Customer();
        dogsCustomer.setName("Alan McDog");
        
        epicDog.setCustomer(dogsCustomer);
        dogDao.updateDog(epicDog);
        
        epicDogCopy = dogDao.getDogByID(epicDog.getId());
        assertTrue(epicDogCopy.getCustomer().equals(dogsCustomer));
    }
}
