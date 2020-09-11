package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
import CarSalesSystem.Entities.UsedCar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class UsedCarEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    public UsedCar createUsedCar(UsedCar car){
        em.persist(car);
        return car;
    }
    
    public List<UsedCar> getCarList(){
        TypedQuery<UsedCar> query = em.createNamedQuery("usedCarFindAll",UsedCar.class);
        return query.getResultList();
    }
    
    public List<UsedCar> searchByReferenceNumber(String referenceNumber){
        TypedQuery<UsedCar> query = em.createNamedQuery("usedCarFindByReference",UsedCar.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();
    }

}
