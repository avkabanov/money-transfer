package com.revolut.kabanov.controller;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.inject.Inject;
import com.revolut.kabanov.model.Client;
import com.revolut.kabanov.model.exception.ClientNotFoundException;
import com.revolut.kabanov.model.exception.RequestValidationException;
import com.revolut.kabanov.model.request.CreateClientRequest;
import com.revolut.kabanov.model.response.CreateClientResponse;
import com.revolut.kabanov.model.response.GetAllClientsResponse;
import com.revolut.kabanov.model.response.GetClientResponse;
import com.revolut.kabanov.service.client.ClientCache;
import com.revolut.kabanov.service.client.ClientValidator;

/**
 * @author Алексей
 * 
 */
@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
public class ClientController {
    
    private final ClientCache clientCache;
    private final ClientValidator clientValidator;

    @Inject
    public ClientController(ClientCache clientCache,
                            ClientValidator clientValidator) {
        this.clientCache = clientCache;
        this.clientValidator = clientValidator;
    }

    @GET
    public Response getAllClients() {
        Collection<Client> allClients = clientCache.getAllClients();
        if (allClients.isEmpty()) {
            Response.noContent().build();
        }

        return Response.ok(new GetAllClientsResponse(allClients)).build();
    }
     
    @GET
    @Path("{id}")
    public Response getClient(@PathParam("id") long id) throws ClientNotFoundException {
        Client client = clientCache.getClient(id);
        return Response.ok(new GetClientResponse(client)).build();
    }

    @POST
    public Response createClient(CreateClientRequest request) throws RequestValidationException {
        clientValidator.validateCreateClientRequest(request);
        Client client = new Client(request.getName(), request.getBalance());
        client = clientCache.createOrUpdateClient(client);
        return Response.ok(new CreateClientResponse(client)).build();
    }
}
