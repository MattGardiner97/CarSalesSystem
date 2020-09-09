package CarSalesSystem.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class CustomerEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    public List<CarSalesSystem.Entities.Customer> findCustomer() {
        TypedQuery<CarSalesSystem.Entities.Customer> query = em.createNamedQuery("customerFindAll", CarSalesSystem.Entities.Customer.class);
        return query.getResultList();
    }
    
    public CarSalesSystem.Entities.Customer createCustomer(CarSalesSystem.Entities.Customer c){
        em.persist(c);
        return c;
    }

}
