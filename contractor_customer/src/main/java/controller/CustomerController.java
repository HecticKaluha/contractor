package controller;

import controller.jsonbody.CustomerBody;
import model.Customer;
import service.CustomerService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
public class CustomerController {
    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return Response.ok(customers).build();
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(CustomerBody customerBody)
    {
        try
        {
            Customer customer = customerService.addCustomer(customerBody.getName(), customerBody.getAdress(), customerBody.getBankAccount(),customerBody.getSex(),customerBody.getAge(),customerBody.getEmail());
            return Response.ok(customer).build();
        }
        catch(Exception e)
        {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }
}
