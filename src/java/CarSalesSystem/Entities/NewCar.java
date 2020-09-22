//File: NewCar.java
//Author: Matthew Gardiner - s0270923
//Last modified: 16/8/2020
//Purpose: Subclass of the Car class representing a new car
package CarSalesSystem.Entities;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "newCarFindAll", query = "SELECT c FROM NewCar c"), //Retrieves all cars from the database
    @NamedQuery(name = "newCarFindByReference", query = "SELECT c FROM NewCar c WHERE c.referenceNumber = :referenceNumber") //Retrieves a car by reference number
})
@Inheritance(strategy = InheritanceType.JOINED) //Indicates to use the joined-subclass strategy
public class NewCar extends Car implements Serializable {    
    @Column(nullable = false)
    private int warrantyYears;

    private int extendedWarrantyYears;
    private String roadsideAssistance;

    //Standard constructor
    public NewCar() {
    }

    /**
     * @return the warrantyYears
     */
    public int getWarrantyYears() {
        return warrantyYears;
    }

    /**
     * @param warrantyYears the warrantyYears to set
     */
    public void setWarrantyYears(int warrantyYears) {
        this.warrantyYears = warrantyYears;
    }

    /**
     * @return the extendedWarrantyYears
     */
    public int getExtendedWarrantyYears() {
        return extendedWarrantyYears;
    }

    /**
     * @param extendedWarrantyYears the extendedWarrantyYears to set
     */
    public void setExtendedWarrantyYears(int extendedWarrantyYears) {
        this.extendedWarrantyYears = extendedWarrantyYears;
    }

    /**
     * @return the roadsideAssistance
     */
    public String getRoadsideAssistance() {
        return roadsideAssistance;
    }

    /**
     * @param roadsideAssistance the roadsideAssistance to set
     */
    public void setRoadsideAssistance(String roadsideAssistance) {
        this.roadsideAssistance = roadsideAssistance;
    }

}
