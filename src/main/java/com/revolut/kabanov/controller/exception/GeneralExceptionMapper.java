package com.revolut.kabanov.controller.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revolut.kabanov.model.exception.ClientNotFoundException;
import com.revolut.kabanov.model.exception.InsufficietFundsException;

import static javax.ws.rs.core.Response.status;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Throwable> {
    private static final Logger log = LoggerFactory.getLogger(GeneralExceptionMapper.class);

    @Override
    public Response toResponse(Throwable e) {
        log.warn("Serializing error to JSON...", e);
        Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;
        String message = e.getMessage();
        Object entity = new Error(message);
        if (e instanceof WebApplicationException) {
            int statusCode = ((WebApplicationException) e).getResponse().getStatus();
            status = Response.Status.fromStatusCode(statusCode);
            if (e instanceof com.sun.jersey.api.NotFoundException) {
                message = "Can't find requested resource";
                entity = message;
            } 
        } else if (e instanceof ClientNotFoundException) {
            status = Response.Status.NOT_FOUND;
            entity = e.getMessage();
        } else if (e instanceof InsufficietFundsException) {
            status = Response.Status.BAD_REQUEST;
            entity = e.getMessage();
        }
        return status(status)
                .entity(entity)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

}