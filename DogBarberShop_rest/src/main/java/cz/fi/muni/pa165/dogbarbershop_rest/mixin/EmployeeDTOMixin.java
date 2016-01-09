package cz.fi.muni.pa165.dogbarbershop_rest.mixin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import cz.fi.muni.pa165.dogbarber.dto.ServiceDTO;
import java.util.Set;



/**
 *
 * @author Severin Simko
 */

public interface EmployeeDTOMixin {
    
    @JsonIgnore
    abstract Set<ServiceDTO> getServices();
    
    
}
