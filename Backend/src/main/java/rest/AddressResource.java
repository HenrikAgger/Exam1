/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.AddressDTO;
import facades.AddressFacade;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

/**
 *
 * @author Henrik
 */

@Path("address")
public class AddressResource {
        
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory(
                "pu",
                "jdbc:mysql://localhost:3307/exam1",
                "dev",
                "ax2",
                EMF_Creator.Strategy.CREATE);
    private static final AddressFacade FACADE =  AddressFacade.getAddressFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
            
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }
    
    // Create an Address
    @POST 
    @Path("create")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public String createAddress(AddressDTO addressDTO){
        FACADE.addAddress(addressDTO);
        return "{\"msg\":\"Address created\"}";
    }
    
    // Find an Address
    @GET
    @Path("id/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public AddressDTO findAddress(@PathParam("id") Long id) {
        AddressDTO a = FACADE.getAddress(id);
        return a;
    }
    
    // Get all Addresses
    @GET
    @Path("all")
    @Produces({MediaType.APPLICATION_JSON})
    public List<AddressDTO> getAllAddresses() {
        return FACADE.getAllAddresses().getAll();
    }
    
    // Count no of addresses
    @GET
    @Path("count")
    @Produces({MediaType.APPLICATION_JSON})
    public String getPersonCount() {
        long count = FACADE.getAddressCount();
        return "{\"count\":"+count+"}";
    }
    
}
