//File: Customer.java
//Author: Matthew Gardiner - s0270923
//Last modified: 16/8/2020
//Purpose: Class representing a customer entity
package CarSalesSystem.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "customerFindAll", query = "SELECT c FROM Customer c") //Finds all customers
    ,
    @NamedQuery(name = "customerFindByName", query = "SELECT c FROM Customer c WHERE lower(c.name) = lower(:name)") //Finds a customer by name
    ,
    @NamedQuery(name = "customerFindById", query = "SELECT c FROM Customer c WHERE c.id = :id")
})
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    private String phoneNumber;
    private String emailAddress;

    //Represents all orders made by a customer
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn
    private List<CustomerOrder> customerOrders;

    //Standard constructor
    public Customer() {
        customerOrders = new ArrayList<CustomerOrder>();
    }

    //Constructor containing only the required fields
    public Customer(String name, String address) {
        this();

        this.name = name;
        this.address = address;
    }

    //Constructor for required and non-required fields
    public Customer(String name, String address, String phoneNumber, String emailAddress) {
        this();

        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the emailAddress
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * @param emailAddress the emailAddress to set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * @return the customerOrders
     */
    public List<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    /**
     * @param customerOrders the customerOrders to set
     */
    public void setCustomerOrders(List<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

    /*
    @Override
    public String toString() {
        return name;
    }
    */

}
