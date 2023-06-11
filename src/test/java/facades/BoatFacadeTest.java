package facades;

import entities.Boat;
import entities.Harbour;
import entities.Owner;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class BoatFacadeTest {

    private static EntityManagerFactory emf;
    private static BoatFacade facade;

    public BoatFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = BoatFacade.getBoatFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
    }

    // Setup the DataBase in a known state BEFORE EACH TEST
    //TODO -- Make sure to change the code below to use YOUR OWN entity class
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("boat.deleteAllRows").executeUpdate();
            em.createNamedQuery("harbour.deleteAllRows").executeUpdate();
            Harbour harbour = new Harbour("Harbour", "Test", 100);
            Boat boat1 = new Boat("name1","make1","brand","image1");
            Boat boat2 = new Boat("name2","make2","brand","image2");
            boat1.setHarbour(harbour);
            boat2.setHarbour(harbour);
            em.persist(harbour);
            em.persist(boat1);
            em.persist(boat2);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    public void testgetBoatsInAHabour() throws Exception {
        System.out.println("getBoatsInAHabour");
        Assertions.assertEquals(2, facade.getBoatsInAHabour("Harbour").size(), "Expects two rows in the database");
    }
    

}
