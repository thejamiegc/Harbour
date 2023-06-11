package facades;

import dtos.BoatDTO;
import dtos.OwnerDTO;
import entities.Boat;
import entities.Harbour;
import entities.Owner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import java.util.List;

public class BoatFacade {
    private static BoatFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private BoatFacade() {}

    // Method returns an instance of the FacadeExample class
    public static BoatFacade getBoatFacade(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            emf = entityManagerFactory;
            instance = new BoatFacade();
        }
        return instance;
    }

    // Method returns EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }


    public List<BoatDTO> getBoatsInAHabour(String harbourName) {
        EntityManager entityManager = emf.createEntityManager();
        Long harbourId = getHarbourIdByName(harbourName);
        if(harbourId == null) {
            return null;
        }
        TypedQuery<Boat> query = entityManager.createQuery("SELECT b FROM Boat b join b.harbour h where h.id =:harbourId", Boat.class);
        query.setParameter("harbourId", harbourId);
        List<Boat> boats = query.getResultList();
        return BoatDTO.getDtos(boats);
    }

    private Long getHarbourIdByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Harbour> query = em.createQuery("SELECT h FROM Harbour h WHERE h.name =:name",Harbour.class);
        query.setParameter("name",name);
        return query.getResultList().get(0).getId();
    }

}
