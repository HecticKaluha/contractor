package controller;

import model.Product;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class productcontroller {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts()
    {
        try{
            List<Product> products = productService.getAllProducts();
            return Response.ok(products).build();
        }
        catch(Exception e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }

    }

}
