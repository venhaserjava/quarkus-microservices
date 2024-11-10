package com.tecnopar.client;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.tecnopar.dto.ProductDTO;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/products")
@RegisterRestClient
@ApplicationScoped
public interface ProductClient {

    @GET
    @Path("/{id}")
    ProductDTO getProductById(@PathParam("{id}") Long id);
}
