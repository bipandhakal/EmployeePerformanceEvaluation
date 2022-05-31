package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.CriteriaTrueFalse;
import com.syntech.repository.CriteriaTrueFalseRepository;
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
@Path("/criteriaTrueFalse")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriteriaTrueFalseRestApi {

    @Inject
    private CriteriaTrueFalseRepository criteriaTrueFalseRepository;

    @POST
    public Response createCriteriaTrueFalse(CriteriaTrueFalse criteriaTrueFalse) throws JsonProcessingException {
        criteriaTrueFalseRepository.create(criteriaTrueFalse);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaTrueFalse);

        return RestResponse.responseBuilder("true", "201", "criteriaTrueFalse created successfully", str);
    }

    @GET
    public Response getAllCriteriaTrueFalse() throws JsonProcessingException {
        List<CriteriaTrueFalse> criteriaTrueFalseList = criteriaTrueFalseRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaTrueFalseList);

        return RestResponse.responseBuilder("true", "200", "List of criteriaTrueFalse", str);
    }

    @GET
    @Path("{id}")
    public Response getCriteriaTrueFalseById(@PathParam("id") Long id) throws JsonProcessingException {
        CriteriaTrueFalse emp = criteriaTrueFalseRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaTrueFalse with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "CriteriaTrueFalse with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateCriteriaTrueFalse(@PathParam("id") Long id, CriteriaTrueFalse criteriaTrueFalse) throws JsonProcessingException {
        CriteriaTrueFalse emp = criteriaTrueFalseRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaTrueFalse with id " + id + " not found", null);
        }
        if (!criteriaTrueFalse.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " CriteriaTrueFalse id mismatch", null);
        }
        criteriaTrueFalseRepository.edit(criteriaTrueFalse);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaTrueFalse);

        return RestResponse.responseBuilder("true", "200", "criteriaTrueFalse updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCriteriaTrueFalse(@PathParam("id") Long id) throws JsonProcessingException {
        CriteriaTrueFalse emp = criteriaTrueFalseRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaTrueFalse with id " + id + " not found", null);
        }
        criteriaTrueFalseRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "CriteriaTrueFalse with id " + id + " deleted successfully", null);
    }
}
