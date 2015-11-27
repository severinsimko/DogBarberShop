/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.facade;

import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import cz.fi.muni.pa165.dogbarber.dto.ServiceChangeNameDTO;

import java.util.List;

/**
 *
 * @author Severin Simko
 */
public interface ServiceFacade {
    
    void changeServiceName(ServiceChangeNameDTO dto);
    
    /*
    Will add employee with specific id to the service with specific id
    */
    void addEmployee(Long serviceId, Long employeeId );
    
    /*
    Will delete an employee from a service.
    */
    void deleteEmployee(Long serviceId, Long employeeId);
    
    /*
    Will return collection of allServices currently available
    */
    List<ServiceDTO> getAllServices();
    
    /*
    Will find a service with a specific id
    */
    ServiceDTO getServiceById(Long id);
    
    /*
    Create Service 
    @param DTO of a serviceCreateDTO
    @return  Long value of a new Service created
    */
    Long createService(ServiceDTO s);
    
    
    
    /*
    Will delete service with defined Id
    */
    void deleteService(Long serviceId);
    
    /*
    Update Service
    */
    void updateService(ServiceDTO s);
    
}
