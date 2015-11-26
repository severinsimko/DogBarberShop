package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.DogDao;
import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Dog;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.beanutils.BeanComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Severin Simko
 */
@Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceDao serviceDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DogDao dogDao;

    @Override
    public void create(cz.fi.muni.pa165.dogbarber.entity.Service service) {

        serviceDao.createService(service);
    }

    @Override
    public void remove(cz.fi.muni.pa165.dogbarber.entity.Service serv) {
        serviceDao.removeService(serv);
    }

    @Override
    public cz.fi.muni.pa165.dogbarber.entity.Service update(cz.fi.muni.pa165.dogbarber.entity.Service serv) {
        return serviceDao.updateService(serv);

    }

    @Override
    public cz.fi.muni.pa165.dogbarber.entity.Service findById(Long id) {
        return serviceDao.findbyId(id);

    }

    @Override
    public List<cz.fi.muni.pa165.dogbarber.entity.Service> getAllServices() {
        return serviceDao.getAllServices();

    }

    @Override
    public List<cz.fi.muni.pa165.dogbarber.entity.Service> getServicesByName(String name) {

        return serviceDao.findByName(name);
    }

    @Override
    public void addEmployee(cz.fi.muni.pa165.dogbarber.entity.Service serv, Employee emp) {

        if (serv.getAllEmployees().contains(emp)) {
            throw new DogBarberException("This service with the id:" + serv.getId() + "already contains the employee with the id" + emp.getId());
        } else {
            serv.addEmployee(emp);
        }
    }

    @Override
    public void removeEmployee(cz.fi.muni.pa165.dogbarber.entity.Service serv, Employee emp) {
        if (serv.getAllEmployees().contains(emp)) {
            throw new DogBarberException("The service with the id" + serv.getId() + "does not contain the employee with id" + emp.getId());
        } else {
            serv.removeEmployee(emp);
        }

    }

    @Override
    public void changeServiceName(cz.fi.muni.pa165.dogbarber.entity.Service service, String name) {
        service.setServiceName(name);
    }

    /*
     Non-trivial method for Service service layer
     This method will return sorted list according to the prices of services.
        
     Services from the cheapest one to the most expensive.
     */
    public List<cz.fi.muni.pa165.dogbarber.entity.Service> sortedServicesByPrice() {

        List<cz.fi.muni.pa165.dogbarber.entity.Service> allServices = new ArrayList<>();
        //unsorted
        allServices = serviceDao.getAllServices();

        //sorting
        BeanComparator fieldComparator = new BeanComparator(
                "price");

        Collections.sort(allServices, fieldComparator);
        return allServices;
    }

   /* public Set<cz.fi.muni.pa165.dogbarber.entity.Service> getTheMostPopularServices() {
        
        //The list of all dogs
        List<Dog> dogs = new ArrayList<>();
        dogs = dogDao.getAllDogs();

        HashMap<cz.fi.muni.pa165.dogbarber.entity.Service, Integer> popularity = new HashMap<>();
        for (Dog d : dogs) {

            Set<cz.fi.muni.pa165.dogbarber.entity.Service> servicesPerDog = new HashSet<>();
            // For each dog I got a Set of his  Services
            servicesPerDog.addAll(d.getServices());

            for (cz.fi.muni.pa165.dogbarber.entity.Service s : servicesPerDog) {

                if (popularity.containsKey(s)) {
                    
                    //If this service is already in HashMap then just increase the value +1
                    Integer i = popularity.get(s);
                    i += 1;
                    
                    //clear the services per dog Set, to be able to load new services for another dog
                    servicesPerDog.clear();
                } else {
                    //If the service is not in HashMap yet, then put Service : 1 in to the HashMap
                    popularity.put(s, 1);
                    //clear the services per dog Set, to be able to load new services for another dog
                    servicesPerDog.clear();
                }

            }

        }

        Set<cz.fi.muni.pa165.dogbarber.entity.Service> popular = new HashSet<cz.fi.muni.pa165.dogbarber.entity.Service>(popularity.keySet());

        return popular;
    }*/

}
