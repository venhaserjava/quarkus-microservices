package com.tecnopar.controller;

import com.tecnopar.dto.BudgetDTO;
import com.tecnopar.service.BudgetService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
//import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
//import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/budget")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BudgetController {

    @Inject
    BudgetService budgetService;

    @GET
    public Response getAll() { 
        try {
            return Response.ok(budgetService.findAll()).build();

        } catch (Exception e) {
            // TODO: handle exception
            return Response.serverError().build();
        }
    }
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        try {
            return Response.ok(budgetService.getById(id)).build();
        } catch (Exception e) {
            // TODO: handle exception
            return Response.serverError().build();
        }
    }
    @POST    
    @Transactional
    public Response create(BudgetDTO dto) {
        try {
            budgetService.create(dto);
            return Response.ok().build();
        } catch (Exception e) {
            // TODO: handle exception
            return Response.serverError().build();
        }
    }
/*    
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id,BudgetDTO dto) {
        try {
            return Response.accepted(budgetService.update(id, dto)).build();
        } catch (Exception e) {
            // TODO: handle exception
            return Response.serverError().build();
        }
    }
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        try {
            budgetService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            // TODO: handle exception
            return Response.serverError().build();
        }
    }
*/
}
