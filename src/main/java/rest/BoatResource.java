package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.BoatFacade;
import facades.OwnerFacade;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("boat")
public class BoatResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final BoatFacade FACADE =  BoatFacade.getBoatFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String HelloBoat() {
        return GSON.toJson("hello boat");
    }

    @Path("{harbourName}")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getBoatsByHarbourName(@PathParam("harbourName") String harbourName) {
        return GSON.toJson(FACADE.getBoatsInAHabour(harbourName));
    }
}
