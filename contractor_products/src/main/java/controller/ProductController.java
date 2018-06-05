package controller;

import controller.jsonbody.ProductBody;
import model.Customer;
import model.Product;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {

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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(ProductBody productBody)
    {
        try
        {
            Product product = productService.addProduct(productBody.getBrand(),productBody.getModel(), productBody.getTracks(),productBody.getHp(),productBody.getPrice(),productBody.getDescription());
            return Response.ok(product).build();
        }
        catch(Exception e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
