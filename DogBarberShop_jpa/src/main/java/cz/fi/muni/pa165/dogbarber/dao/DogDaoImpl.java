package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Dog;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Pavel Drobek
 */
@Repository
public class DogDaoImpl implements DogDao {
    
    @PersistenceContext
    EntityManager em;

    @Override
    public void addDog(Dog dog) {
        em.persist(dog);
    }

    @Override
    public void removeDog(Dog dog) {
        em.remove(dog);
    }

    @Override
    public Dog getDogByID(Long id) {
        return em.find(Dog.class, id);
    }

    @Override
    public List<Dog> getAllDogs() {
        return (List<Dog>)em.createQuery("select d from Dog d", Dog.class).getResultList();
    }

    @Override
    public Dog updateDog(Dog dog) {
       return em.merge(dog);
    }
}
