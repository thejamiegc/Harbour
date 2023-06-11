package facades;

import entities.Boat;
import entities.Owner;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

//Uncomment the line below, to temporarily disable this test
//@Disabled
public class OwnerFacadeTest {

    private static EntityManagerFactory emf;
    private static OwnerFacade facade;

    public OwnerFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
       emf = EMF_Creator.createEntityManagerFactoryForTest();
       facade = OwnerFacade.getOwnerFacade(emf);
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
            em.createNamedQuery("owner.deleteAllRows").executeUpdate();
            em.createNamedQuery("boat.deleteAllRows").executeUpdate();
            Owner owner1 = new  Owner("Some txt", "More text", 12345678);
            Owner owner2 = new  Owner("aaa", "bbb", 87654321);
            Owner owner3 = new  Owner("ccc", "ddd", 87654321);
            Boat boat1 = new Boat("name1","make1","brand","image1");
            owner1.addBoat(boat1);
            owner2.addBoat(boat1);
            em.persist(boat1);
            em.persist(owner1);
            em.persist(owner2);
            em.persist(owner3);
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
    public void testGetAllOwners() throws Exception {
        System.out.println("getAllOwners");
        assertEquals(3, facade.getAllOwners().size(), "Expects two rows in the database");
    }

    @Test
    public void testgetOwnersOfABoat() throws Exception {
        System.out.println("getOwnersOfABoat");
        assertEquals(2, facade.getOwnersOfABoat("name1").size(), "Expects two rows in the database");
    }
    

}
