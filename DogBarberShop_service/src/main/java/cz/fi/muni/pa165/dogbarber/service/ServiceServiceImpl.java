
package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.entity.Service;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.util.Collection;
import javax.inject.Inject;

/**
 *
 * @author Severin Simko
 */
public class ServiceServiceImpl implements ServiceService{
    
    @Inject
    private ServiceDao serviceDao;
    
    @Inject
    private EmployeeDao employeeDao;
    
    @Override
    public void create(Service service){
    
        serviceDao.createService(service);
    }
    
    @Override
    public void remove(Service serv){
    serviceDao.removeService(serv);
    }
    
    @Override
    public Service update(Service serv){
        return serviceDao.updateService(serv);
    
    }
    
    @Override
    public Service findById(Long id){
       return serviceDao.findbyId(id);    
    
    }
    
    @Override
    public Collection<Service> getAllServices(){
    return serviceDao.getAllServices();
    
    }
    
    @Override
    public Collection<Service> getServicesByName(String name){
    return null;
    }
    
    @Override
    public void addEmployee(Service serv, Employee emp){
    
        if(serv.getAllEmployees().contains(emp)){
            throw new DogBarberException("This service with the id:"+serv.getId()+"already contains the employee with the id" +emp.getId());
        }else{
            serv.addEmployee(emp);
        }
    }
    
    @Override
    public void removeEmployee(Service serv, Employee emp){
        if(serv.getAllEmployees().contains(emp)){
            throw new DogBarberException("The service with the id"+serv.getId()+"does not contain the employee with id"+ emp.getId());
        }else{
        serv.removeEmployee(emp);
        }
    
    }
    
    @Override
    public void changeServiceName(Service service, String name){
    service.setServiceName(name);
    }
    
    
}
