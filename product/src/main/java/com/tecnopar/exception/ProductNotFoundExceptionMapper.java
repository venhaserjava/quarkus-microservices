package com.tecnopar.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;

public class ProductNotFoundExceptionMapper  implements ExceptionMapper<ProductNotFoundException> {


    @Override
    public Response toResponse(ProductNotFoundException exception) {
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(),"Produto não encontrado").build();
    }
}
