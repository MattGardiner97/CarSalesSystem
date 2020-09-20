
package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class CarEJB {
     @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    public Car createCar(Car car){
        em.persist(car);
        return car;
    }
    
    public List<Car> getCarList(){
        TypedQuery<Car> query = em.createNamedQuery("carFindAll",Car.class);
        return query.getResultList();
    }
    
    public List<Car> searchByReferenceNumber(String referenceNumber){
        TypedQuery<Car> query = em.createNamedQuery("carFindByReference",Car.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }
}
