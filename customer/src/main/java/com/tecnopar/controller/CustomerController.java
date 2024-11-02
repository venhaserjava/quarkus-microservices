package com.tecnopar.controller;

import com.tecnopar.dto.CustomerDTO;
import com.tecnopar.repository.CustomerRepository;
import com.tecnopar.service.CustomerService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerController {
    @Inject
    CustomerService customerService;

//    private final CustomerService customerService;
//
//    public CustomerController(CustomerService customerService) {
//        this.customerService = customerService;
//    }

    @GET
    public Response getAll() {
        try {
            return  Response.ok(customerService.findAll()).build();
        } catch (Exception e){
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/{id")
    public Response getById(@PathParam("id") Long id){
        try {
            return Response.ok(customerService.mapToDTO(customerService.findById(id))).build();
        } catch (Exception e) {
            return  Response.serverError().build();
        }
    }
    @POST
    @Transactional
    public Response create(CustomerDTO dto){
        try {
            return Response.ok(customerService.create(dto)).build();
        } catch (Exception e) {
            return  Response.serverError().build();
        }
    }
    @PUT
    @Transactional
    @Path("/{id")
    public Response update(@PathParam("id") Long id, CustomerDTO dto) {
        try {
            return Response.accepted(customerService.update(id,dto)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
    @DELETE
    @Transactional
    @Path("/{id")
    public Response delete(@PathParam("id") Long id) {
        try {
            customerService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}