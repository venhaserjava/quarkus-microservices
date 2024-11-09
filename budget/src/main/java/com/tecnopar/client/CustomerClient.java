package com.tecnopar.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.tecnopar.dto.CustomerDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/customers")
@RegisterRestClient
@ApplicationScoped
public interface CustomerClient {
    @GET
    @Path("/{id}")
    CustomerDTO getCustomerById(@PathParam("id") Long id);
}
