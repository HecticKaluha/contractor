package controller;


import service.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.Order;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProducts() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return Response.ok(orders).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{invoiceid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("invoiceid") String invoiceid) {
        try {
            boolean deleted = orderService.deleteOrder(Integer.parseInt(invoiceid));
            return Response.ok("Order "+ invoiceid + " succesfully deleted").build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
