package com.gitlab.saschakr.dummyrest.api;


import com.gitlab.saschakr.dummyrest.dao.EmployeeDAO;
import com.gitlab.saschakr.dummyrest.entity.Employee;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Tag(name = "Employee", description = "All API-Requests for Employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/employee")
public class EmployeeEndpoint {

    @Inject
    private EmployeeDAO employeeDAO;

    @GET
    public List<Employee> getEmployee() {
        return employeeDAO.load();
    }

    @POST
    public Employee createEmployee(final Employee employee) {
        return this.employeeDAO.create(employee);
    }

    @PUT
    @Path("/{id}")
    public Employee updateEmployee(@PathParam("id") final long id,
                                   final Employee employee) {
        return this.employeeDAO.update(id, employee);
    }

    @DELETE
    @Path("/{id}")
    public Employee deleteEmployee(@PathParam("id") final long id) {
        return this.employeeDAO.delete(id);
    }
}
