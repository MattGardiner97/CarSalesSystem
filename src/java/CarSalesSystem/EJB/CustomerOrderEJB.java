//File: CustomerOrder.java
//Author: Jack Pashley - 12002954
//Last modified: 16/9/2020
//Purpose: Class representing a customer order entity

package CarSalesSystem.EJB;

import CarSalesSystem.Entities.Car;
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

    //Creates the list to search through and presist
    public CustomerOrder createCustomerOrder(CustomerOrder c){
        //System.out.println(c.);
        //car = em.find(Car.class, c.getId());
        //System.out.println("Car is class: " + car);
        em.persist(c);
        System.out.println("ORDER ID: " + c.getId());
        //car = em.find(Car.class, car.getId());
        //System.out.println("Car Class: " + car.getClass() + "\nCar Details: " + car.getQuantity());
        System.out.println("Car ID: " + c.getOrderedCar().getId() + "\nCar Quantity: " + c.getOrderedCar().getQuantity());
        return c;
        //System.out.println("ID IS: " + id);
        //car = em.find(Car.class, id);
        //System.out.println(car.getClass());
                /*TypedQuery<UsedCar> query = em.createNamedQuery("usedCarFindByReference",UsedCar.class);
        query.setParameter("referenceNumber", referenceNumber);
        return query.getResultList();*/
    }

    //returns all orders
    public List<CustomerOrder> findCustomerOrder() {
        TypedQuery<CustomerOrder> query = em.createNamedQuery("orderFindAll", CustomerOrder.class);
        return query.getResultList();
    }

}
