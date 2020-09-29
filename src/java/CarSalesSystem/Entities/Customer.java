//File: Customer.java
//Author: Matthew Gardiner - s0270923
//Last modified: 16/8/2020
//Purpose: Class representing a customer entity
package CarSalesSystem.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

 @Entity
@NamedQueries({
    @NamedQuery(name = "customerFindAll", query = "SELECT c FROM Customer c") //Finds all customers
    ,
    @NamedQuery(name = "customerFindByName", query = "SELECT c FROM Customer c WHERE lower(c.name) = lower(:name)") //Finds a customer by name
    ,
    @NamedQuery(name = "customerFindByID", query = "SELECT c FROM Customer c WHERE c.id = :id")
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
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    @JoinColumn
    private List<CustomerOrder> customerOrders;

    //Standard constructor
    public Customer() {
        customerOrders = new ArrayList<CustomerOrder>();
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
 

}
