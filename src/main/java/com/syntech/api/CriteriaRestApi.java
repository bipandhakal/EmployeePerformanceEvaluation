package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.Criteria;
import com.syntech.repository.CriteriaRepository;
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
@Path("/criteria")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriteriaRestApi {

    @Inject
    private CriteriaRepository criteriaRepository;

    @POST
    public Response createCriteria(Criteria criteria) throws JsonProcessingException {
        criteriaRepository.create(criteria);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteria);

        return RestResponse.responseBuilder("true", "201", "criteria created successfully", str);
    }

    @GET
    public Response getAllCriterias() throws JsonProcessingException {
        List<Criteria> criteriaList = criteriaRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteriaList);

        return RestResponse.responseBuilder("true", "200", "List of criterias", str);
    }

    @GET
    @Path("{id}")
    public Response getCriteriaById(@PathParam("id") Long id) throws JsonProcessingException {
        Criteria emp = criteriaRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Criteria with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "Criteria with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateCriteria(@PathParam("id") Long id, Criteria criteria) throws JsonProcessingException {
        Criteria emp = criteriaRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Criteria with id " + id + " not found", null);
        }
        if (!criteria.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " Criteria id mismatch", null);
        }
        criteriaRepository.edit(criteria);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(criteria);

        return RestResponse.responseBuilder("true", "200", "criteria updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCriteria(@PathParam("id") Long id) throws JsonProcessingException {
        Criteria emp = criteriaRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Criteria with id " + id + " not found", null);
        }
        criteriaRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "Criteria with id " + id + " deleted successfully", null);
    }
}
