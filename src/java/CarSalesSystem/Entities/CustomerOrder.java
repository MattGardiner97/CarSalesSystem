//File: CustomerOrder.java
//Author: Jack Pashley - 12002954
//Co-Author: Matthew Gardiner - s0270923
//Last modified: 16/9/2020
//Purpose: Class representing a customer order entity
package CarSalesSystem.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity

@NamedQueries({
    @NamedQuery(name = "orderFindAll", query = "SELECT o FROM CustomerOrder o"), //Finds all orders
    @NamedQuery(name = "orderFindId", query = "SELECT o FROM CustomerOrder o WHERE o.id = :id"), //Finds an order by order number
})

public class CustomerOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    private Car orderedCar;

    @Column(nullable = false)
    private int quantity;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    //Standard constructor
    public CustomerOrder() {
    }

    //Constructor containing all required fields
    public CustomerOrder(Customer customer, Car orderedCar, int quantity) {
        this.customer = customer;
        this.orderedCar = orderedCar;
        this.quantity = quantity;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return the orderedCar
     */
    public Car getOrderedCar() {
        return orderedCar;
    }

    /**
     * @param orderedCar the orderedCar to set
     */
    public void setOrderedCar(Car orderedCar) {
        this.orderedCar = orderedCar;
    }

    /**
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt the createdAt to set
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
