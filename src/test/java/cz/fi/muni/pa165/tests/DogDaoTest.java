package cz.fi.muni.pa165.tests;

import cz.fi.muni.pa165.dogbarber.enums.Color;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.main.InMemoryDB;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import static org.testng.Assert.assertEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 *
 * @author Crumby
 */

@ContextConfiguration(classes=PersistenceSampleApplicationContext.class)
public class DogDaoTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @PersistenceUnit
    private static EntityManagerFactory emf;
    
    private static Dog epicDog;
    private static Long epicDogId;
    
    @BeforeClass
    public static void prepare(){
        new AnnotationConfigApplicationContext(InMemoryDB.class);
        
        emf = Persistence.createEntityManagerFactory("default");
        
        epicDog = new Dog("Alik", "Chivavua", 14, Color.BLACK);
        epicDogId = epicDog.getId();
    }
    
    @Test
    public void dogAddtionTest(){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(epicDog);
        em.getTransaction().commit();
        em.close();
        
        Dog epicDogCopy;
        
        EntityManager eMan = emf.createEntityManager();
        eMan.getTransaction().begin();
        epicDogCopy = eMan.find(Dog.class, epicDogId);
        eMan.getTransaction().commit();
        eMan.close();
        
        assertEquals("Wrong or none dog found", epicDog.getName(), epicDogCopy.getName());
    }
}
