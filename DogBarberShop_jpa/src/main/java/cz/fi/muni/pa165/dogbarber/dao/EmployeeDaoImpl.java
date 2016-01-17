package cz.fi.muni.pa165.dogbarber.dao;

import cz.fi.muni.pa165.dogbarber.entity.Employee;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import java.util.HashSet;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Michal Brath
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public void addEmployee(Employee employee) {
		em.persist(employee);
	};

	@Override
	public void removeEmployee(Employee employee) {
		em.remove(employee);
	};

	@Override
	public Employee getEmployeeByID(Long Id) {
		return em.find(Employee.class, Id);
	};

	@Override
	public List<Employee> findByName(String name) {
		return em
				.createQuery("SELECT a FROM Employee a WHERE a.name = :name",
						Employee.class).setParameter("name", name)
				.getResultList();
	}

	@Override
	public Set<Employee> getAllEmployees() {
		return new HashSet<Employee>(em.createQuery("select c from Employee c",
				Employee.class).getResultList());
	};

	@Override
	public Employee updateEmployee(Employee employee) {
		return em.merge(employee);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		try {
			return em
					.createQuery("SELECT e FROM Employee e where email=:email",
							Employee.class).setParameter("email", email)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}