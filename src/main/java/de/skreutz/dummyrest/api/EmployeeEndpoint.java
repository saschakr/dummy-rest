package de.skreutz.dummyrest.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import org.eclipse.microprofile.metrics.annotation.Metered;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;

import de.skreutz.dummyrest.dao.EmployeeDAO;
import de.skreutz.dummyrest.entity.Employee;

@Tag(name = "Employee", description = "All API-Requests for Employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/employee")
@Metered(name = "EmployeeEndpoint")
public class EmployeeEndpoint {

  @Inject
  private EmployeeDAO employeeDAO;

  @GET
  public List<Employee> getEmployees() {
    return employeeDAO.load();
  }

  @GET
  @Path("/{id}")
  public Employee getEmployee(@PathParam("id") final long id) {
    return this.employeeDAO.get(id);
  }

  @POST
  public Employee createEmployee(final Employee employee) {
    return this.employeeDAO.create(employee);
  }

  @PUT
  @Path("/{id}")
  public Employee updateEmployee(@PathParam("id") final long id, final Employee employee) {
    return this.employeeDAO.update(id, employee);
  }

  @DELETE
  @Path("/{id}")
  public Employee deleteEmployee(@PathParam("id") final long id) {
    return this.employeeDAO.delete(id);
  }
}
