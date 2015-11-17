package cz.fi.muni.pz165.tests;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
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
        
/*        dogDao.addDog(epicDog);
        
        epicDogCopy = dogDao.getDogByID(epicDog.getId());
        
        assertEquals(epicDog.getName(), epicDogCopy.getName());
        assertEquals(epicDog.getAge(), epicDogCopy.getAge());
        assertEquals(epicDog.getBreed(), epicDogCopy.getBreed());
        assertEquals(epicDog.getColor(), epicDogCopy.getColor());
        assertEquals(epicDog.getId(), epicDogCopy.getId());*/
    }
}
