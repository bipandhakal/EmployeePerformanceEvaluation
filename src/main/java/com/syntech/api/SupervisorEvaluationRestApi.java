package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.SupervisorEvaluation;
import com.syntech.repository.SupervisorEvaluationRepository;
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
@Path("/supervisorEvaluation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SupervisorEvaluationRestApi {

    @Inject
    private SupervisorEvaluationRepository supervisorEvaluationRepository;

    @POST
    public Response createSupervisorEvaluation(SupervisorEvaluation supervisorEvaluation) throws JsonProcessingException {
        supervisorEvaluationRepository.create(supervisorEvaluation);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(supervisorEvaluation);

        return RestResponse.responseBuilder("true", "201", "supervisorEvaluation created successfully", str);
    }

    @GET
    public Response getAllSupervisorEvaluation() throws JsonProcessingException {
        List<SupervisorEvaluation> supervisorEvaluationList = supervisorEvaluationRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(supervisorEvaluationList);

        return RestResponse.responseBuilder("true", "200", "List of supervisorEvaluation", str);
    }

    @GET
    @Path("{id}")
    public Response getSupervisorEvaluationById(@PathParam("id") Long id) throws JsonProcessingException {
        SupervisorEvaluation emp = supervisorEvaluationRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " SupervisorEvaluation with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "SupervisorEvaluation with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateSupervisorEvaluation(@PathParam("id") Long id, SupervisorEvaluation supervisorEvaluation) throws JsonProcessingException {
        SupervisorEvaluation emp = supervisorEvaluationRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " SupervisorEvaluation with id " + id + " not found", null);
        }
        if (!supervisorEvaluation.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " SupervisorEvaluation id mismatch", null);
        }
        supervisorEvaluationRepository.edit(supervisorEvaluation);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(supervisorEvaluation);

        return RestResponse.responseBuilder("true", "200", "supervisorEvaluation updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteSupervisorEvaluation(@PathParam("id") Long id) throws JsonProcessingException {
        SupervisorEvaluation emp = supervisorEvaluationRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " SupervisorEvaluation with id " + id + " not found", null);
        }
        supervisorEvaluationRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "SupervisorEvaluation with id " + id + " deleted successfully", null);
    }
}
