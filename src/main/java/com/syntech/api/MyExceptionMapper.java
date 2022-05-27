package com.syntech.api;

import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author bipan
 */
@Provider
public class MyExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger logger = Logger.getLogger(MyExceptionMapper.class.getName());

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        logger.log(Level.WARNING, "Constraint violationException: {0}", exception.getMessage());
        StringBuilder message = new StringBuilder();
        exception.getConstraintViolations().stream().forEachOrdered(cv -> {
            message.append(cv.getMessage());
        });
        JsonObject json = jsonFromString(message.toString(), exception);
        String msg = json.toString();

        return RestResponse.responseBuilder("false", "404", msg, null);
    }

//    private String prepareMessage(ConstraintViolationException exception) {
//
//        String msg = "";
////        final StringBuilder strBuilder = new StringBuilder();
//
//        for (ConstraintViolation<?> cv : exception.getConstraintViolations()) {
////            msg += cv.getMessage();
////            strBuilder.append(cv.getMessage());
//            msg = msg + cv.getMessage();
//        }
//        return msg;
//    }
    private JsonObject jsonFromString(String jsonObjectStr, ConstraintViolationException ex) {
        JsonObject object = null;
        try {
            JsonReader jsonReader = Json.createReader(new StringReader(jsonObjectStr));
            object = jsonReader.readObject();
        } catch (Exception e) {
            logger.log(Level.WARNING, "Constraint violationException: jsonFromString: {0}", e.getMessage());
            JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
            ex.getConstraintViolations().forEach(violation -> {
                for (Path.Node node : violation.getPropertyPath()) {
                    jsonObjectBuilder.add(node.getName(), Json.createArrayBuilder().add(violation.getMessage()));
                }
            });
            object = jsonObjectBuilder.build();
        }
        return object;
    }
}
