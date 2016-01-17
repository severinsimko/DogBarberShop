package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import java.util.List;

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
}
