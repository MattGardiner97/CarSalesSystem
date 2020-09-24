package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
import CarSalesSystem.Entities.NewCar;
import CarSalesSystem.Entities.UsedCar;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CarEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;

    public Car createCar(Car car) {
        em.persist(car);
        return car;
    }

    public Car findByID(long ID){
        TypedQuery<Car> query = em.createNamedQuery("carFindByID",Car.class);
        query.setParameter("id", ID);
        return query.getSingleResult();
    }
    
    public List<Car> getCarList() {
        TypedQuery<Car> query = em.createNamedQuery("carFindAll", Car.class);
        return query.getResultList();
    }

    public List<NewCar> getNewCarList() {
        TypedQuery<NewCar> query = em.createNamedQuery("newCarFindAll", NewCar.class);
        return query.getResultList();
    }
    
    public List<UsedCar> getUsedCarList(){
        TypedQuery<UsedCar> query = em.createNamedQuery("usedCarFindAll",UsedCar.class);
        return query.getResultList();
    }

    public List<NewCar> searchNewCarByReferenceNumber(String referenceNumber){
        TypedQuery<NewCar> query = em.createNamedQuery("newCarFindByReference",NewCar.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }
    
    public List<UsedCar> searchUsedCarByReferenceNumber(String referenceNumber){
        TypedQuery<UsedCar> query = em.createNamedQuery("usedCarFindByReference",UsedCar.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }
}
