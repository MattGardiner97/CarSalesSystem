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
    public CustomerOrder createCustomerOrder(CustomerOrder c){
        em.persist(c);
        System.out.println("ORDER ID: " + c.getId());
        car = em.find(Car.class, c.getOrderedCar().getId());
        System.out.println("Car ID: " + car.getId() + "\nCar Quantity: " + car.getQuantity() + "\n Make & Model: " + car.getMake() + ", " + car.getModel());
        car.setQuantity(car.getQuantity() - c.getQuantity());
        System.out.println("Updated Car Quantity: " + car.getQuantity());
        em.merge(car);
        
        customer = em.find(Customer.class, c.getCustomer().getId());
        customer.setOrderNumber(customer.getOrderNumber() + 1);
        em.merge(customer);
        return c;
    }

    //returns all orders
    public List<CustomerOrder> findCustomerOrder() {
        TypedQuery<CustomerOrder> query = em.createNamedQuery("orderFindAll", CustomerOrder.class);
        return query.getResultList();
    }

}
