package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("owner")
public class OwnerResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final OwnerFacade FACADE =  OwnerFacade.getOwnerFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String GetAllOwners() {
        return GSON.toJson(FACADE.getAllOwners());
    }

    @Path("{boatName}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getOwnerByBoatName(@PathParam("boatName") String boatName) {
        return GSON.toJson(FACADE.getOwnersOfABoat(boatName));
    }
}
