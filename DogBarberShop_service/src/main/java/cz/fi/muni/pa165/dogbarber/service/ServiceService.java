/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Severin Simko
 */
public interface ServiceService {
    void create(Service serv);
    void remove(Service serv);
    Service update(Service serv);
    Service findById(Long id);
    List<Service> getAllServices();
    List<Service> getServicesByName(String name);
    void addEmployee(Service serv, Employee emp);
    void removeEmployee(Service serv, Employee emp);
    void changeServiceName(Service service, String name);
    List<Service> sortedServicesByPrice();
    //Set<Service> getTheMostPopularServices();
    
}
