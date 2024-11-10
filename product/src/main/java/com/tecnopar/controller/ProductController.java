package com.tecnopar.controller;

import com.tecnopar.dto.ProductDTO;
import com.tecnopar.service.ProductService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    public Response getAll() {
        try {
            return Response.ok(productService.FindAll()).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
    /*
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        try {
            return  Response.ok(productService.getById(id)).build();
        } catch (Exception e) {
            return  Response.serverError().build();
        }
    }
    */
    @GET
    @Path("{id}")
    public Response getProductById(@PathParam("id") Long id) {
        try {
            return Response.ok(productService.getById(id)).build();            
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @POST
    @Transactional
    public  Response create(ProductDTO dto){
        try {
            return Response.ok(productService.create(dto)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
    @PUT
    @Transactional
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, ProductDTO dto){
        try {
            //productService.update(id,dto);
            return Response.accepted(productService.update(id,dto)).build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
    @DELETE
    @Transactional
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            productService.delete(id);
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }
}
