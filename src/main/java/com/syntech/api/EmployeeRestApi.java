package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.Employee;
import com.syntech.repository.EmployeeRepository;
import java.util.List;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

//    @Inject
    RestResponse restResponse = new RestResponse();

    @POST
    @Path("create")
    public Response createEmployee(Employee employee) throws JsonProcessingException {
        employeeRepository.create(employee);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employee);

        Response obj = restResponse.jsonMethod("true", "201", "employee created successfully", str);
        return Response.status(Response.Status.OK).entity(obj).build();

//
//        JsonObject json = Json.createObjectBuilder()
//                .add("success", "true")
//                .add("code", "201")
//                .add("message", "employee created successfully")
//                .add("result", str).build();
//
//        return Response.status(Response.Status.OK).entity(json).build();
    }

    @GET
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GET
    @Path("{id}")
    public Employee getEmployeeById(@PathParam("id") Long id) {
        return employeeRepository.findById(id);
    }

//    @PUT
//    @Path("{id}")
//    public Response updateEmployee(@PathParam("id") Employee employee) {
//        employeeRepository.edit(employee);
//        return Response.status(200).entity(employee).build();
//    }
//    @DELETE
//    @Path("{id}")
//    public Response deleteEmployee(@PathParam("id") Employee employee) {
//        employeeRepository.delete(employee);
//        return Response.status(200).entity(employee).build();
//
//    }
}
