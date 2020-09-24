//File: CustomerOrder.java
//Author: Jack Pashley - 12002954
//Last modified: 16/9/2020
//Purpose: Class representing a customer order entity
package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
import CarSalesSystem.Entities.Customer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import CarSalesSystem.Entities.CustomerOrder;

@Stateless
public class CustomerOrderEJB {

    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    private Car car;
    private Customer customer;

    //Creates the list to search through and presist
    public CustomerOrder createCustomerOrder(CustomerOrder order) {
        em.persist(order);
        car = em.find(Car.class, order.getOrderedCar().getId());
        car.setQuantity(car.getQuantity() - order.getQuantity());
        em.merge(car);

        customer = em.find(Customer.class, order.getCustomer().getId());
        customer.getCustomerOrders().add(order);
        em.merge(customer);
        return order;
    }

    //returns all orders
    public List<CustomerOrder> getAllCustomerOrders() {
        TypedQuery<CustomerOrder> query = em.createNamedQuery("orderFindAll", CustomerOrder.class);
        return query.getResultList();
    }

    public List<CustomerOrder> searchById(Long search) {
        TypedQuery<CustomerOrder> query = em.createNamedQuery("orderFindId", CustomerOrder.class).setParameter("id", search);
        return query.getResultList();
    }
    
    public void deleteByID(long ID){
        CustomerOrder order = em.find(CustomerOrder.class, ID);
        em.remove(order);
    }
}
