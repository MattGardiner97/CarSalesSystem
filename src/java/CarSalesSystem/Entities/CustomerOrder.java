//File: CustomerOrder.java
//Author: Matthew Gardiner - s0270923
//Last modified: 16/8/2020
//Purpose: Class representing a customer order entity
package CarSalesSystem.Entities;

import java.io.Serializable;
import java.time.LocalDate;
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
    private int salePrice;

    @Column(nullable = false)
    private String salesPerson;

    @Column(nullable = false)
    private boolean fulfilled;

    @Column(nullable = false)
    private LocalDate deliveryDate;

    //Standard constructor
    public CustomerOrder() {
    }

    //Constructor containing all required fields
    public CustomerOrder(Car orderedCar, String referenceNumber, int salePrice, String salesPerson, boolean fulfilled, LocalDate deliveryDate) {
        this.orderedCar = orderedCar;
        this.referenceNumber = referenceNumber;
        this.salePrice = salePrice;
        this.salesPerson = salesPerson;
        this.fulfilled = fulfilled;
        this.deliveryDate = deliveryDate;
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
     * @return the salePrice
     */
    public int getSalePrice() {
        return salePrice;
    }

    /**
     * @param salePrice the salePrice to set
     */
    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return the salesPerson
     */
    public String getSalesPerson() {
        return salesPerson;
    }

    /**
     * @param salesPerson the salesPerson to set
     */
    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    /**
     * @return the fulfilled
     */
    public boolean isFulfilled() {
        return fulfilled;
    }

    /**
     * @param fulfilled the fulfilled to set
     */
    public void setFulfilled(boolean fulfilled) {
        this.fulfilled = fulfilled;
    }

    /**
     * @return the deliveryDate
     */
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
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
}
