package com.syntech.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syntech.adapter.ReportGenerator;
import com.syntech.model.Employee;
import com.syntech.model.Months;
import com.syntech.model.Report;
import com.syntech.repository.EmployeeRepository;
import com.syntech.repository.MonthsRepository;
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
    private MonthsRepository monthsRepository;

    @Inject
    private ReportGenerator reportGenerator;

    @GET
    @Path("{id}/{id1}")
    public Response getFinalReportById(@PathParam("id") Long id, @PathParam("id1") Long id1) throws JsonProcessingException {
        Employee emp = employeeRepository.findById(id);
        Months month = monthsRepository.findById(id1);
        if (emp == null && month == null) {
            return RestResponse.responseBuilder("false", "404", " Employee with id " + id + " and month with id " + id1 + " not found", null);
        }
        if (emp == null) {
            return RestResponse.responseBuilder("false", "404", " Employee with id " + id + " not found", null);
        }
        if (month == null) {
            return RestResponse.responseBuilder("false", "404", " Month with id " + id1 + " not found", null);
        }

        List<Report> reportList = reportGenerator.prepareReport(emp, month);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(reportList);

        return RestResponse.responseBuilder("true", "200", "The final report of employee having id " + id + " and month " + id1 + " is:", str);
    }
}
