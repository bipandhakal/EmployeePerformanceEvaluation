package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bipan
 */
@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeRestApi {

    @Inject
    private EmployeeRepository employeeRepository;

    @POST
    public Response createEmployee(Employee employee) throws JsonProcessingException {
        if (employee.getFirstName() == null || employee.getLastName() == null || employee.getJoinDate() == null) {
            return RestResponse.responseBuilder("false", "400", " Employee fields should not be null", null);
        }
        employeeRepository.create(employee);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employee);

        return RestResponse.responseBuilder("true", "201", "employee created successfully", str);
    }

    @GET
    public Response getAllEmployees() throws JsonProcessingException {
        List<Employee> employeeList = employeeRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employeeList);

        return RestResponse.responseBuilder("true", "200", "List of employees", str);
    }

    @GET
    @Path("{id}")
    public Response getEmployeeById(@PathParam("id") Long id) throws JsonProcessingException {

        Employee emp = employeeRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Employee with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "Employee with id " + id + " found", str);
    }

    @PUT
//    @Path("{id}")
    public Response updateEmployee(Employee employee) throws JsonProcessingException {
//        employeeRepository.findById(id);
        employeeRepository.edit(employee);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employee);

        return RestResponse.responseBuilder("true", "200", "employee updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployee(@PathParam("id") Long id) throws JsonProcessingException {
        employeeRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "Employee with id " + id + " deleted successfully", null);
    }
}
