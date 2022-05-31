package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.EmployeeAchievements;
import com.syntech.repository.EmployeeAchievementsRepository;
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
@Path("/employeeAchievements")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeAchievementsRestApi {

    @Inject
    private EmployeeAchievementsRepository employeeAchievementsRepository;

    @POST
    public Response createEmployeeAchievements(EmployeeAchievements employeeAchievements) throws JsonProcessingException {
        employeeAchievementsRepository.create(employeeAchievements);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employeeAchievements);

        return RestResponse.responseBuilder("true", "201", "employeeAchievements created successfully", str);
    }

    @GET
    public Response getAllEmployeeAchievements() throws JsonProcessingException {
        List<EmployeeAchievements> employeeAchievementsList = employeeAchievementsRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employeeAchievementsList);

        return RestResponse.responseBuilder("true", "200", "List of employeeAchievements", str);
    }

    @GET
    @Path("{id}")
    public Response getEmployeeAchievementsById(@PathParam("id") Long id) throws JsonProcessingException {
        EmployeeAchievements emp = employeeAchievementsRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " EmployeeAchievements with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "EmployeeAchievements with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateEmployeeAchievements(@PathParam("id") Long id, EmployeeAchievements employeeAchievements) throws JsonProcessingException {
        EmployeeAchievements emp = employeeAchievementsRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " EmployeeAchievements with id " + id + " not found", null);
        }
        if (!employeeAchievements.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " EmployeeAchievements id mismatch", null);
        }
        employeeAchievementsRepository.edit(employeeAchievements);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(employeeAchievements);

        return RestResponse.responseBuilder("true", "200", "employeeAchievements updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteEmployeeAchievements(@PathParam("id") Long id) throws JsonProcessingException {
        EmployeeAchievements emp = employeeAchievementsRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " EmployeeAchievements with id " + id + " not found", null);
        }
        employeeAchievementsRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "EmployeeAchievements with id " + id + " deleted successfully", null);
    }
}
