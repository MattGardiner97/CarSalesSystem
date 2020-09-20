package CarSalesSystem.EJB;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import CarSalesSystem.Entities.Customer;

@Stateless
public class CustomerEJB {
   
    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;

    public Customer createCustomer(Customer c){
        em.persist(c);
        return c;
    }

    public List<Customer> getCustomerList() {
        TypedQuery<Customer> query = em.createNamedQuery("customerFindAll", Customer.class);
        return query.getResultList();
    }

    public List<Customer> searchByName(String search){
        TypedQuery<Customer> query = em.createNamedQuery("customerFindByName", Customer.class);
        query.setParameter("name", search);
        return query.getResultList();
    }
      
    public Customer findByID(long ID){
        return em.find(Customer.class, ID);
    }
}
