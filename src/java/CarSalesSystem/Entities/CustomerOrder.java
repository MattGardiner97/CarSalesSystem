//File: CustomerOrder.java
//Author: Matthew Gardiner - s0270923
//Co-Author: Jack Pashley - 12002954
//Last modified: 16/9/2020
//Purpose: Class representing a customer order entity
package CarSalesSystem.Entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "orderFindAll", query = "SELECT o FROM CustomerOrder o") //Finds all orders
    ,
    @NamedQuery(name = "orderFindByReferenceNumber", query = "SELECT o FROM CustomerOrder o WHERE o.referenceNumber = :referenceNumber") //Finds an order by reference number
})
public class CustomerOrder implements Serializable {
    //Ideally, a 'Customer' field would have been included with this class containing a reference
    //to the customer who made this order, however the requirement specification explicity stated
    //that the relationship between a customer and customer order be unidirectional, therefore the
    //Customer field is not included.
        
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String referenceNumber;

    @OneToOne(fetch = FetchType.LAZY)
    private Car orderedCar;

    @Column(nullable = false)
    private int quantity;

    //Standard constructor
    public CustomerOrder() {
    }

    //Constructor containing all required fields
    public CustomerOrder(Car orderedCar, String referenceNumber) {
        this.orderedCar = orderedCar;
        this.referenceNumber = referenceNumber;
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
     * @return the referenceNumber
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * @param referenceNumber the referenceNumber to set
     */
    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
    
    /**
     * @return the quantity
     */
    public int getQuantity(){
        return quantity;
    }
    
    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
}
