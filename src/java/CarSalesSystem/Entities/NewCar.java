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
    private String accessories;

    //Standard constructor
    public NewCar() {
    }

    //Constructor containing all required fields
    public NewCar(String referenceNumber, String make, String model, int buildYear, String transmission, String driveType, String engine, String fuelType, int warrantyYears) {
        super(referenceNumber, make, model, buildYear, transmission, driveType, engine, fuelType);
        this.warrantyYears = warrantyYears;
    }

    //COnstructor containing required and non-required fields
    public NewCar(String referenceNumber, String make, String model, int buildYear, String transmission, String driveType, String engine, String fuelType, int warrantyYears, int extendedWarrantyYears, String accessories) {
        super(referenceNumber, make, model, buildYear, transmission, driveType, engine, fuelType);
        this.warrantyYears = warrantyYears;
        this.extendedWarrantyYears = extendedWarrantyYears;
        this.accessories = accessories;
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
     * @return the accessories
     */
    public String getAccessories() {
        return accessories;
    }

    /**
     * @param accessories the accessories to set
     */
    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }
}
