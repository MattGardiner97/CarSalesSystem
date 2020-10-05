//Filename: CustomerOrderEJB.java
//Purpose: EJB for CustomerOrder related operations
package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
import CarSalesSystem.Entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import CarSalesSystem.Entities.CustomerOrder;
import java.util.Date;

@Stateless
public class CustomerOrderEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    private Car car;
    private Customer customer;

    //Persist a new CustomerOrder
    public CustomerOrder createCustomerOrder(CustomerOrder order) {
        //Set the creation date
        order.setCreatedAt(new Date());
        em.persist(order);
        
        //Update the ordered Car's quantity
        car = em.find(Car.class, order.getOrderedCar().getId());
        car.setQuantity(car.getQuantity() - order.getQuantity());
        em.merge(car);

        customer = em.find(Customer.class, order.getCustomer().getId());
        customer.getCustomerOrders().add(order);
        em.merge(customer);
        return order;
    }

    //Get all CustomerOrders
    public List<CustomerOrder> getAllCustomerOrders() {
        TypedQuery<CustomerOrder> query = em.createNamedQuery("orderFindAll", CustomerOrder.class);
        return query.getResultList();
    }

    //Find a CustomerOrder by ID
    public List<CustomerOrder> searchById(Long search) {
        TypedQuery<CustomerOrder> query = em.createNamedQuery("orderFindId", CustomerOrder.class).setParameter("id", search);
        return query.getResultList();
    }

    //Delete a CustomerOrder by ID
    public void deleteByID(long ID) {
        CustomerOrder order = em.find(CustomerOrder.class, ID);
        Car orderedCar = em.find(Car.class, order.getOrderedCar().getId());
        orderedCar.setQuantity(orderedCar.getQuantity() + order.getQuantity());
        em.merge(orderedCar);
        em.remove(order);
    }
}
