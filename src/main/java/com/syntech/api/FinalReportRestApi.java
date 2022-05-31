package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.adapter.ReportGenerator;
import com.syntech.model.Employee;
import com.syntech.model.Report;
import com.syntech.repository.EmployeeRepository;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author bipan
 */
@Path("/report")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FinalReportRestApi {

    @Inject
    private EmployeeRepository employeeRepository;

    @Inject
    private ReportGenerator reportGenerator;

    @GET
    @Path("{id}")
    public Response getFinalReportById(@PathParam("id") Long id) throws JsonProcessingException {
        Employee emp = employeeRepository.findById(id);
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Employee with id " + id + " not found", null);
        }

        List<Report> reportList = reportGenerator.prepareReport(emp);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(reportList);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String str = gson.toJson(reportList);

        return RestResponse.responseBuilder("true", "200", "The final report of employee with id " + id + " is:", str);
    }
}
