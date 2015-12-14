package cz.fi.muni.pa165.dogbarber.config;

import cz.fi.muni.pa165.dogbarber.facade.DogFacadeImpl;
import cz.fi.muni.pa165.dogbarber.facade.ServiceFacadeImpl;
import cz.fi.muni.pa165.dogbarber.main.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.dogbarber.service.DogServiceImpl;
import cz.fi.muni.pa165.dogbarber.service.ServiceServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackageClasses={ServiceServiceImpl.class, ServiceFacadeImpl.class, DogServiceImpl.class, DogFacadeImpl.class})
public class ServiceConfiguration {
	

	@Bean
	public Mapper dozer(){
		return new DozerBeanMapper();
	}
	

	
}