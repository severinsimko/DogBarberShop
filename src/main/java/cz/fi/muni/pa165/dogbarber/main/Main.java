package cz.fi.muni.pa165.dogbarber.main;


import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.math.BigDecimal;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Severin Simko
 */

public class Main {

    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(InMemoryDB.class);
        
        emf = Persistence.createEntityManagerFactory("default");
        
        task();
        
        emf.close();

        
    }
    
   public static void task(){
   
       EntityManager em = emf.createEntityManager() ;
   
       em.getTransaction().begin();
       
       Service service = new Service();
       service.setLengthInMinutes(90);
       service.setPrice(new BigDecimal(120.20));
       service.setServiceName("umytie");
       
       Employee employee = new Employee();
       em.persist(employee);
       em.persist(service);
       
       service.addEmployee(employee);
       
       
       
       
       em.getTransaction().commit();
       em.close();
   } 

}
