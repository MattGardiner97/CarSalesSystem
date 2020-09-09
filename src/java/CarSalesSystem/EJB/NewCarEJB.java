package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
import CarSalesSystem.Entities.NewCar;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class NewCarEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    public NewCar createNewCar(NewCar car){
        em.persist(car);
        return car;
    }
    
    public List<NewCar> getCarList(){
        TypedQuery<NewCar> query = em.createNamedQuery("newCarFindAll",NewCar.class);
        return query.getResultList();
    }

}
