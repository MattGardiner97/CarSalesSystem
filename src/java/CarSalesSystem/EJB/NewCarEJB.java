package CarSalesSystem.EJB;

import CarSalesSystem.Entities.NewCar;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class NewCarEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    public NewCar createNewCar(NewCar car){
        em.persist(car);
        return car;
    }

}
