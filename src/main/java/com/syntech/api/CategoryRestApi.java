package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.model.Category;
import com.syntech.repository.CategoryRepository;
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
@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRestApi {

    @Inject
    private CategoryRepository categoryRepository;

    @POST
    public Response createCategory(Category category) throws JsonProcessingException {
        categoryRepository.create(category);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(category);

        return RestResponse.responseBuilder("true", "201", "category created successfully", str);
    }

    @GET
    public Response getAllCategories() throws JsonProcessingException {
        List<Category> categoryList = categoryRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(categoryList);

        return RestResponse.responseBuilder("true", "200", "List of categories", str);
    }

    @GET
    @Path("{id}")
    public Response getCategoryById(@PathParam("id") Long id) throws JsonProcessingException {
        Category emp = categoryRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Category with id " + id + " not found", null);
        }

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(emp);
        return RestResponse.responseBuilder("true", "200", "Category with id " + id + " found", str);
    }

    @PUT
    @Path("{id}")
    public Response updateCategory(@PathParam("id") Long id, Category category) throws JsonProcessingException {
        Category emp = categoryRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Category with id " + id + " not found", null);
        }
        if (!category.getId().equals(emp.getId())) {
            return RestResponse.responseBuilder("false", "404", " Category id mismatch", null);
        }
        categoryRepository.edit(category);
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(category);

        return RestResponse.responseBuilder("true", "200", "category updated successfully", str);
    }

    @DELETE
    @Path("{id}")
    public Response deleteCategory(@PathParam("id") Long id) throws JsonProcessingException {
        Category emp = categoryRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Category with id " + id + " not found", null);
        }
        categoryRepository.deleteById(id);
        return RestResponse.responseBuilder("true", "200", "Category with id " + id + " deleted successfully", null);
    }
}
