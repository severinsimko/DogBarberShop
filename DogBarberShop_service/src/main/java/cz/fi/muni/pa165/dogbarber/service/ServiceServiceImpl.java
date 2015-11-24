
package cz.fi.muni.pa165.dogbarber.service;

import cz.fi.muni.pa165.dogbarber.dao.EmployeeDao;
import cz.fi.muni.pa165.dogbarber.dao.ServiceDao;
import cz.fi.muni.pa165.dogbarber.entity.Employee;
import cz.fi.muni.pa165.dogbarber.exception.DogBarberException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Severin Simko
 */
@Service
public class ServiceServiceImpl implements ServiceService{
    
    @Autowired
    private ServiceDao serviceDao;
    
    @Autowired
    private EmployeeDao employeeDao;
    
    @Override
    public void create(cz.fi.muni.pa165.dogbarber.entity.Service service){
    
        serviceDao.createService(service);
    }
    
    @Override
    public void remove(cz.fi.muni.pa165.dogbarber.entity.Service serv){
    serviceDao.removeService(serv);
    }
    
    @Override
    public cz.fi.muni.pa165.dogbarber.entity.Service update(cz.fi.muni.pa165.dogbarber.entity.Service serv){
        return serviceDao.updateService(serv);
    
    }
    
    @Override
    public cz.fi.muni.pa165.dogbarber.entity.Service findById(Long id){
       return serviceDao.findbyId(id);    
    
    }
    
    @Override
    public List<cz.fi.muni.pa165.dogbarber.entity.Service> getAllServices(){
    return serviceDao.getAllServices();
    
    }
    
    @Override
    public List<cz.fi.muni.pa165.dogbarber.entity.Service> getServicesByName(String name){
    
        
        
        return serviceDao.findByName(name);
    }
    
    @Override
    public void addEmployee(cz.fi.muni.pa165.dogbarber.entity.Service serv, Employee emp){
    
        if(serv.getAllEmployees().contains(emp)){
            throw new DogBarberException("This service with the id:"+serv.getId()+"already contains the employee with the id" +emp.getId());
        }else{
            serv.addEmployee(emp);
        }
    }
    
    @Override
    public void removeEmployee(cz.fi.muni.pa165.dogbarber.entity.Service serv, Employee emp){
        if(serv.getAllEmployees().contains(emp)){
            throw new DogBarberException("The service with the id"+serv.getId()+"does not contain the employee with id"+ emp.getId());
        }else{
        serv.removeEmployee(emp);
        }
    
    }
    
    @Override
    public void changeServiceName(cz.fi.muni.pa165.dogbarber.entity.Service service, String name){
    service.setServiceName(name);
    }
    
    
}
