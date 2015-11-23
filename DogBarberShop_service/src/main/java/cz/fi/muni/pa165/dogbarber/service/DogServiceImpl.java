package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Pavel Drobek
 */
public class DogServiceImpl implements DogService {

    @Inject
    DogDao dogDao;
    
    @Override
    public void createDog(Dog dog) {
        dogDao.addDog(dog);
    }

    @Override
    public void deleteDog(Dog dog) {
        dogDao.removeDog(dog);
    }

    @Override
    public Dog getDogByID(Long dogId) {
        return dogDao.getDogByID(dogId);
    }

    @Override
    public List<Dog> getAllDogs() {
        return dogDao.getAllDogs();
    }

    //TODO: TEST THIS!!!
    @Override
    public void subscribeDogForAService(Dog dog, Service service) {
        dog.addService(service);
        dogDao.updateDog(dog);
    }
    
    //TODO: TEST THIS TOO!
    @Override
    public void unsubscribeDogForAService(Dog dog,Service service) {
        dog.removeService(service);
        dogDao.updateDog(dog);
    }
    
}
