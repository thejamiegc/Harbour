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

public class OwnerFacade {
    private static OwnerFacade instance;
    private static EntityManagerFactory emf;

    //Private Constructor to ensure Singleton
    private OwnerFacade() {}

    // Method returns an instance of the FacadeExample class
    public static OwnerFacade getOwnerFacade(EntityManagerFactory entityManagerFactory) {
        if (instance == null) {
            emf = entityManagerFactory;
            instance = new OwnerFacade();
        }
        return instance;
    }

    // Method returns EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<OwnerDTO> getAllOwners() {
        EntityManager entityManager = emf.createEntityManager();
        TypedQuery<Owner> query = entityManager.createQuery("SELECT o FROM Owner o", Owner.class);
        List<Owner> owners = query.getResultList();
        return OwnerDTO.getDtos(owners);
    }

    public List<OwnerDTO> getOwnersOfABoat(String boatName) {
        EntityManager entityManager = emf.createEntityManager();
        Long boatId = getBoatIdByName(boatName);
        if(boatId == null) {
            return null;
        }
        TypedQuery<Owner> query = entityManager.createQuery("SELECT o FROM Owner o JOIN o.boats b WHERE b.id =:boatid", Owner.class);
        query.setParameter("boatid", boatId);
        List<Owner> owners = query.getResultList();
        return OwnerDTO.getDtos(owners);
    }

    private Long getBoatIdByName(String name) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Boat> query = em.createQuery("SELECT b FROM Boat b WHERE b.name =:name",Boat.class);
        query.setParameter("name",name);
        return query.getResultList().get(0).getId();
    }

}
