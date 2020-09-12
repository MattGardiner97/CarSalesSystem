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
    
    @Column(nullable = false)
    private int registrationExpiry;
    
    @Column(nullable = false)
    private String serviceHistory;
    
    @Column(nullable = false)
    private String vehicleIdentificationNumber;

    private String history;

    //Standard constructor
    public UsedCar() {
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
     * @return the registrationExpiry
     */
    public int getRegistrationExpiry() {
        return registrationExpiry;
    }

    /**
     * @param registrationExpiry the registrationExpiry to set
     */
    public void setRegistrationExpiry(int registrationExpiry) {
        this.registrationExpiry = registrationExpiry;
    }

    /**
     * @return the serviceHistory
     */
    public String getServiceHistory() {
        return serviceHistory;
    }

    /**
     * @param serviceHistory the serviceHistory to set
     */
    public void setServiceHistory(String serviceHistory) {
        this.serviceHistory = serviceHistory;
    }

    /**
     * @return the vehicleIdentificationNumber
     */
    public String getVehicleIdentificationNumber() {
        return vehicleIdentificationNumber;
    }

    /**
     * @param vehicleIdentificationNumber the vehicleIdentificationNumber to set
     */
    public void setVehicleIdentificationNumber(String vehicleIdentificationNumber) {
        this.vehicleIdentificationNumber = vehicleIdentificationNumber;
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
