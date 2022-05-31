package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.CriteriaRange;
import com.syntech.repository.CriteriaRangeRepository;
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
@Path("/criteriaRange")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriteriaRangeRestApi {

    @Inject
    private CriteriaRangeRepository criteriaRangeRepository;

    @POST
    public Response createCriteriaRange(CriteriaRange criteriaRange) throws JsonProcessingException {
        criteriaRangeRepository.create(criteriaRange);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaRange);

        return RestResponse.responseBuilder("true", "201", "criteriaRange created successfully", str);
    }

    @GET
    public Response getAllCriteriaRange() throws JsonProcessingException {
        List<CriteriaRange> criteriaRangeList = criteriaRangeRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaRangeList);

        return RestResponse.responseBuilder("true", "200", "List of criteriaRange", str);
    }

    @GET
    @Path("{id}")
    public Response getCriteriaRangeById(@PathParam("id") Long id) throws JsonProcessingException {
        CriteriaRange emp = criteriaRangeRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaRange with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "CriteriaRange with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateCriteriaRange(@PathParam("id") Long id, CriteriaRange criteriaRange) throws JsonProcessingException {
        CriteriaRange emp = criteriaRangeRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaRange with id " + id + " not found", null);
        }
        if (!criteriaRange.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " CriteriaRange id mismatch", null);
        }
        criteriaRangeRepository.edit(criteriaRange);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaRange);

        return RestResponse.responseBuilder("true", "200", "criteriaRange updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCriteriaRange(@PathParam("id") Long id) throws JsonProcessingException {
        CriteriaRange emp = criteriaRangeRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " CriteriaRange with id " + id + " not found", null);
        }
        criteriaRangeRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "CriteriaRange with id " + id + " deleted successfully", null);
    }
}
