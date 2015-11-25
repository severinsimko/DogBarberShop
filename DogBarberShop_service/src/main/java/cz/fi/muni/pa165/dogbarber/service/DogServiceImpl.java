package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author Pavel Drobek
 */
@org.springframework.stereotype.Service
public class DogServiceImpl implements DogService {

    @Inject
    DogDao dogDao;

    @Inject
    ServiceDao serviceDao;

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

    @Override
    public void subscribeDogForAService(Dog dog, Service service) {
        List<Service> services = serviceDao.findByName(service.getServiceName());
        if (services.contains(service)) {
            dog.addService(service);
            dogDao.updateDog(dog);
        } else {
            throw new DogBarberException("No such service in database!");
        }
    }

    @Override
    public void unsubscribeDogForAService(Dog dog, Service service) {
        List<Service> services = serviceDao.findByName(service.getServiceName());
        if (!services.contains(service)){
            throw new DogBarberException("No such service in database!");
        }
        if(dog.getServices().contains(service))
        {
            dog.removeService(service);
            dogDao.updateDog(dog);
        } else {
            throw new DogBarberException("Dog is not subscribed for this service!");
        }
    }

}
