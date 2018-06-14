package controller;


import controller.jsonbody.AddOrderBody;
import service.OrderService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import model.Order;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestScoped
@Path("/order")
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders() {
        try {
            List<Order> orders = orderService.getAllOrders();
            return Response.ok(orders).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{orderid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificOrder(@PathParam("orderid") String orderid) {
        try {
            Order selected = orderService.find(Integer.parseInt(orderid));
            return Response.ok(selected).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrder(AddOrderBody addOrderBody) {
        try{
            int orderid = addOrderBody.getOrderid();
            List<Integer> products = new ArrayList<>();
            for (int i : addOrderBody.getProducts())
                products.add(i);

            Order order = orderService.addOrder(orderid,products);
            return Response.ok(order).build();
        }
        catch (Exception e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{orderid}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("orderid") String orderid) {
        try {
            boolean deleted = orderService.deleteOrder(Integer.parseInt(orderid));
            return Response.ok("Order "+ orderid + " succesfully deleted").build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
