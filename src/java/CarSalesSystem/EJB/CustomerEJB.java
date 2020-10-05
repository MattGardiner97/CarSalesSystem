//Filename: CustomerEJB.java
//Purpose: EJB providing Customer related operations

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
    
    //Persist a new Customer object
    public Customer createCustomer(Customer c){
        em.persist(c);
        return c;
    }

    //Get all Customers
    public List<Customer> getCustomerList() {
        TypedQuery<Customer> query = em.createNamedQuery("customerFindAll", Customer.class);
        return query.getResultList();
    }

    //Find a Customer by name
    public List<Customer> searchByName(String search){
        TypedQuery<Customer> query = em.createNamedQuery("customerFindByName", Customer.class);
        query.setParameter("name", search);
        return query.getResultList();
    }
      
    //Find a Customer by ID
    public Customer findByID(long ID){
        Customer customer = em.find(Customer.class, ID);
        em.refresh(customer);
        return customer;
    }
}
