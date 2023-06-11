package rest;

import entities.Boat;
import entities.EntityExample;
import entities.Owner;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.core.UriBuilder;
import java.net.URI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class OwnerResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    Boat boat1;
    static final URI BASE_URI = UriBuilder.fromUri(SERVER_URL).port(SERVER_PORT).build();
    private static HttpServer httpServer;
    private static EntityManagerFactory emf;

    static HttpServer startServer() {
        ResourceConfig rc = ResourceConfig.forApplication(new ApplicationConfig());
        return GrizzlyHttpServerFactory.createHttpServer(BASE_URI, rc);
    }

    @BeforeAll
    public static void setUpClass() {
        //This method must be called before you request the EntityManagerFactory
        EMF_Creator.startREST_TestWithDB();
        emf = EMF_Creator.createEntityManagerFactoryForTest();

        httpServer = startServer();
        //Setup RestAssured
        RestAssured.baseURI = SERVER_URL;
        RestAssured.port = SERVER_PORT;
        RestAssured.defaultParser = Parser.JSON;
    }

    @AfterAll
    public static void closeTestServer() {
        //System.in.read();

        //Don't forget this, if you called its counterpart in @BeforeAll
        EMF_Creator.endREST_TestWithDB();
        httpServer.shutdownNow();
    }

    @BeforeEach
    void setUp() {
        EntityManager em = emf.createEntityManager();

        Owner owner1 = new Owner("John", "Doe", 12345678);
        Owner owner2 = new Owner("Jane", "Doe", 87654321);
        Owner owner3 = new Owner("Jamie", "Smith", 12348765);
        boat1 = new Boat("Boaty McBoatface", "mcface", "boat", "no");
        owner1.addBoat(boat1);
        owner2.addBoat(boat1);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("owner.deleteAllRows").executeUpdate();
            em.createNamedQuery("boat.deleteAllRows").executeUpdate();
            em.persist(boat1);
            em.persist(owner1);
            em.persist(owner2);
            em.persist(owner3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testServerIsUp() {
        given().when().get("/owner").then().statusCode(200);
    }

    @Test
    void getAllOwners() {
        given()
                .contentType("application/json")
                .get("/owner").then()
                .assertThat()
                .statusCode(200)
                .body("size()", equalTo(3));
    }

    @Test
    void getOwnerByBoatName() {
        given()
                .contentType("application/json")
                .get("/owner/" + boat1.getName()).then()
                .assertThat()
                .statusCode(200)
                .body("size()", equalTo(2));
    }
}