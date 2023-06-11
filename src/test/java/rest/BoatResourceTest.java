package rest;

import entities.Boat;
import entities.Harbour;
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

class BoatResourceTest {

    private static final int SERVER_PORT = 7777;
    private static final String SERVER_URL = "http://localhost/api";
    Harbour harbour1, harbour2;
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
        harbour1 = new Harbour("Harbour1", "Harbour1", 100);
        harbour2 = new Harbour("Harbour2", "Harbour2", 200);
        Boat boat1 = new Boat("Boaty McBoatface", "mcface", "boat", "no");
        Boat boat2 = new Boat("Boaty McBoatface2", "mcface2", "boat2", "no2");
        Boat boat3 = new Boat("Boaty McBoatface3", "mcface3", "boat3", "no3");
        boat1.setHarbour(harbour1);
        boat2.setHarbour(harbour1);
        boat3.setHarbour(harbour2);
        try {
            em.getTransaction().begin();
            em.createNamedQuery("boat.deleteAllRows").executeUpdate();
            em.createNamedQuery("harbour.deleteAllRows").executeUpdate();
            em.persist(harbour1);
            em.persist(harbour2);
            em.persist(boat1);
            em.persist(boat2);
            em.persist(boat3);
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
    public void testgetBoatsByHarbourName(){
        given()
                .contentType("application/json")
                .get("/boat/"+ harbour1.getName()).then()
                .assertThat()
                .statusCode(200)
                .body("size()", equalTo(2));
    }
}