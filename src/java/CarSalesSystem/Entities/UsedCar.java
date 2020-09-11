//File: UsedCar.java
//Author: Matthew Gardiner - s0270923
//Last modified: 16/8/2020
//Purpose: Subclass of the Car class representing a used car
package CarSalesSystem.Entities;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "usedCarFindAll", query = "SELECT c FROM UsedCar c"), //Retrieves all cars from the database
    @NamedQuery(name = "usedCarFindByReference", query = "SELECT c FROM UsedCar c WHERE c.referenceNumber = :referenceNumber") //Retrieves a car by reference number
})
@Inheritance(strategy = InheritanceType.JOINED) //Indicates to use the joined-subclass strategy
public class UsedCar extends Car implements Serializable {

    @Column(nullable = false)
    private int odometer;

    @Column(nullable = false)
    private String registrationNumber;

    private String history;

    //Standard constructor
    public UsedCar() {
    }

    //Constructor with only required fields
    public UsedCar(String referenceNumber, String make, String model, int buildYear, String transmission, String driveType, String engine, String fuelType,
            int odometer, String registrationNumber, LocalDate registrationExpiry) {

        super(referenceNumber, make, model, buildYear, transmission, driveType, engine, fuelType);

        this.odometer = odometer;
        this.registrationNumber = registrationNumber;
    }

    //Constructor with required and non-required fields
    public UsedCar(String referenceNumber, String make, String model, int buildYear, String transmission, String driveType, String engine, String fuelType,
            int odometer, String registrationNumber, LocalDate registrationExpiry, String history) {

        super(referenceNumber, make, model, buildYear, transmission, driveType, engine, fuelType);

        this.odometer = odometer;
        this.registrationNumber = registrationNumber;
        this.history = history;
    }

    /**
     * @return the odometer
     */
    public int getOdometer() {
        return odometer;
    }

    /**
     * @param odometer the odometer to set
     */
    public void setOdometer(int odometer) {
        this.odometer = odometer;
    }

    /**
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * @return the history
     */
    public String getHistory() {
        return history;
    }

    /**
     * @param history the history to set
     */
    public void setHistory(String history) {
        this.history = history;
    }
}
