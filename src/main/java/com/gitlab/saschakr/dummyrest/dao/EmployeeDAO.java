package com.gitlab.saschakr.dummyrest.dao;

import com.gitlab.saschakr.dummyrest.entity.Employee;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmployeeDAO {

    @PersistenceContext(unitName = "employee")
    private EntityManager em;

    public List<Employee> load() {
        return this.em.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
    }

    public Employee create(final Employee employee) {
        final Employee save = new Employee(null, employee.getLastname(), employee.getFirstname(), employee.getAge(), employee.getSalary());
        this.em.persist(save);
        return save;
    }

    public Employee update(final long id, final Employee employee) {

        final Employee loaded = this.em.find(Employee.class, id);
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
        final Employee employee = this.em.find(Employee.class, id);
        if (employee != null) {
            this.em.remove(employee);
        }
        return employee;
    }

}
