package controller;

import controller.jsonbody.ProductBody;
import exceptions.CouldNotFindProductException;
import model.Customer;
import model.Product;
import service.ProductService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.Pattern;
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
    public Response getAllProducts() {
        try {
            List<Product> products = productService.getAllProducts();
            return Response.ok(products).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{brand}/{model}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response findProduct(@PathParam("brand") String brand, @PathParam("model") String model) {
        try {
            Product product = productService.find(brand, model);
            return Response.ok(product).build();
        } catch (CouldNotFindProductException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(ProductBody productBody) {
        try {
            Product product = productService.addProduct(productBody.getBrand(), productBody.getModel(), productBody.getTracks(), productBody.getHp(), productBody.getPrice(), productBody.getDescription());
            return Response.ok(product).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(ProductBody productBody) {
        try {
            Product updatedProduct = productService.updateProduct(productBody.getBrand(), productBody.getModel(), productBody.getTracks(), productBody.getHp(), productBody.getPrice(), productBody.getDescription());
            return Response.ok(updatedProduct).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{brand}/{model}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("brand") String brand, @PathParam("model") String model) {
        try {
            boolean deleted = productService.delete(brand, model);
            return Response.ok("Product "+ brand + "  "+ model + " succesfully deleted").build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
