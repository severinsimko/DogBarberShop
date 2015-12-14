package cz.fi.muni.pa165.dogbarber.main;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Severin Simko
 */

public class Main {

    private static EntityManagerFactory emf;

    @SuppressWarnings("resource")
	public static void main(String[] args) {
        new AnnotationConfigApplicationContext(InMemoryDB.class);
        
        emf = Persistence.createEntityManagerFactory("default");
        emf.close();
    }
}
