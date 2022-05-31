package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.CriteriaSelf;
import com.syntech.repository.CriteriaSelfRepository;
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
@Path("/criteriaSelf")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriteriaSelfRestApi {

    @Inject
    private CriteriaSelfRepository criteriaSelfRepository;

    @POST
    public Response createCriteriaSelf(CriteriaSelf criteriaSelf) throws JsonProcessingException {
        criteriaSelfRepository.create(criteriaSelf);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaSelf);

        return RestResponse.responseBuilder("true", "201", "criteriaSelf created successfully", str);
    }

    @GET
    public Response getAllCriteriaSelf() throws JsonProcessingException {
        List<CriteriaSelf> criteriaSelfList = criteriaSelfRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaSelfList);

        return RestResponse.responseBuilder("true", "200", "List of criteriaSelf", str);
    }

    @GET
    @Path("{id}")
    public Response getCriteriaSelfById(@PathParam("id") Long id) throws JsonProcessingException {
        CriteriaSelf emp = criteriaSelfRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaSelf with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "CriteriaSelf with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateCriteriaSelf(@PathParam("id") Long id, CriteriaSelf criteriaSelf) throws JsonProcessingException {
        CriteriaSelf emp = criteriaSelfRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaSelf with id " + id + " not found", null);
        }
        if (!criteriaSelf.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " CriteriaSelf id mismatch", null);
        }
        criteriaSelfRepository.edit(criteriaSelf);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaSelf);

        return RestResponse.responseBuilder("true", "200", "criteriaSelf updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCriteriaSelf(@PathParam("id") Long id) throws JsonProcessingException {
        CriteriaSelf emp = criteriaSelfRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaSelf with id " + id + " not found", null);
        }
        criteriaSelfRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "CriteriaSelf with id " + id + " deleted successfully", null);
    }
}
