package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;

/**
 *
 * @author Pavel Drobek
 */
@org.springframework.stereotype.Service
public interface DogService {
    void createDog(Dog dog);
    
    void deleteDog(Dog dog);
    
    Dog getDogByID(Long dogId);
    
    List<Dog> getAllDogs();
    
    void subscribeDogForAService(Dog dog, Service service);
    
    void unsubscribeDogForAService(Dog dog, Service service);
}
