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
    @Path("create")
    public Response createEmployee(Employee employee) throws JsonProcessingException {
        employeeRepository.create(employee);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employee);

        return RestResponse.responseBuilder("true", "201", "employee created successfully", str);

//
//        JsonObject json = Json.createObjectBuilder()
//                .add("success", "true")
//                .add("code", "201")
//                .add("message", "employee created successfully")
//                .add("result", str).build();
//
//        return Response.status(Response.Status.OK).entity(json).build();
    }

//    @GET
//    public List<Employee> getAllEmployees() {
//        return employeeRepository.findAll();
//    }
    @GET
    public Response getAllEmployees() throws JsonProcessingException {
        List<Employee> employeeList = employeeRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employeeList);

        return RestResponse.responseBuilder("true", "200", "List of employee", str);
    }

//    @GET
//    @Path("{id}")
//    public Employee getEmployeeById(@PathParam("id") Long id) {
//        return employeeRepository.findById(id);
//    }
    @GET
    @Path("{id}")
    public Response getEmployeeById(@PathParam("id") Long id) throws JsonProcessingException {
        
        Employee emp = employeeRepository.findById(id);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);

        return RestResponse.responseBuilder("true", "200", "Get Employee by Id", str);
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
        return RestResponse.responseBuilder("true", "200", "Employee deleted successfully", "null");
    }
}
