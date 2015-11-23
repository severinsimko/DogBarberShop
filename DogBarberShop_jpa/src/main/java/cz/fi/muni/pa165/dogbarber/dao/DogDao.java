package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Dog;
import java.util.List;

/**
 *
 * @author Pavel Drobek
 */
public interface DogDao {
    void addDog(Dog dog);
    void removeDog(Dog dog);
    void updateDog(Dog dog);
    Dog getDogByID(Long Id);
    List<Dog> getAllDogs();
}
