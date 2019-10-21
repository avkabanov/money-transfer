package com.revolut.kabanov.model.response;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.revolut.kabanov.model.Client;

/**
 * @author Алексей
 * 
 */
public class GetAllClientsResponse {
    
    private List<GetClientResponse> clients = new ArrayList<>();

    public GetAllClientsResponse(Collection<Client> list) {
        clients.clear();
        for (Client client : list) {
            clients.add(new GetClientResponse(client));
        }
    }

    public GetAllClientsResponse() {
    }

    public List<GetClientResponse> getClients() {
        return clients;
    }

    public void setClients(List<GetClientResponse> clients) {
        this.clients = clients;
    }
}
