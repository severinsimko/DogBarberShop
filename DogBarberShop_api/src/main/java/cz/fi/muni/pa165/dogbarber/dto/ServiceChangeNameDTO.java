/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.fi.muni.pa165.dogbarber.dto;

import java.security.Provider;
import java.util.Objects;

/**
 *
 * @author Severin Simko
 */
public class ServiceChangeNameDTO {
    
   private Long serviceId;
   private String serviceName;

    public Long getServiceId() {
        return serviceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
   
  @Override
    public int hashCode() {

        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.serviceName);
        return hash;

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Provider.Service)) {
            return false;
        }
        final ServiceChangeNameDTO other = (ServiceChangeNameDTO) obj;

        if (serviceName == null) {
            if (other.getServiceName() != null) {
                return false;
            }
        } else if (!serviceName.equals(other.getServiceName())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ServiceChangeNameDTO{" + "serviceId=" + serviceId + ", serviceName=" + serviceName + '}';
    }
    
    
}
