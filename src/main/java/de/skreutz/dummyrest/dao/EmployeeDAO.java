package de.skreutz.dummyrest.dao;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import org.eclipse.microprofile.metrics.annotation.Metered;

import java.util.List;

import de.skreutz.dummyrest.entity.Employee;

@Stateless
@Metered(name = "EmployeeDAO")
public class EmployeeDAO {

  @PersistenceContext(unitName = "employee")
  private EntityManager em;

  public List<Employee> load() {
    return this.em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
  }

  public Employee get(final long id) {

    return this.em.find(Employee.class, id);

  }

  public Employee create(final Employee employee) {

    final Employee save = new Employee(null, employee.getLastname(), employee.getFirstname(), employee.getAge(), employee.getSalary());
    this.em.persist(save);

    return save;

  }

  public Employee update(final long id, final Employee employee) {

    final Employee loaded = this.get(id);
    if (loaded != null) {
      loaded.setFirstname(employee.getFirstname());
      loaded.setLastname(employee.getLastname());
      loaded.setAge(employee.getAge());
      loaded.setSalary(employee.getSalary());
      this.em.persist(loaded);

      return loaded;
    }

    return null;
  }

  public Employee delete(final long id) {

    final Employee employee = this.get(id);
    if (employee != null) {
      this.em.remove(employee);
    }

    return employee;

  }

}
