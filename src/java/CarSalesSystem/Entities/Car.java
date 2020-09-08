//File: Car.java
//Author: Matthew Gardiner - s0270923
//Last modified: 14/8/2020
//Purpose: Abstract entity class representing a vehicle.
package CarSalesSystem.Entities;

import java.io.Serializable;
import javax.persistence.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Entity
@NamedQueries({
    @NamedQuery(name = "carFindAll", query = "SELECT c FROM Car c"), //Retrieves all cars from the database
    @NamedQuery(name = "carFindByReferenceNumber", query = "SELECT c FROM Car c WHERE c.referenceNumber = :referenceNumber") //Find a car by reference number
})
@Inheritance(strategy = InheritanceType.JOINED)
@Named(value = "Car")
@SessionScoped
public abstract class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String referenceNumber;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int buildYear;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String driveType;

    @Column(nullable = false)
    private String engine;

    @Column(nullable = false)
    private String fuelType;

    //Default constructor
    public Car() {
    }

    //Constructor containing all required values
    public Car(String referenceNumber, String make, String model, int buildYear, String transmission, String driveType, String engine, String fuelType) {
        this.referenceNumber = referenceNumber;
        this.make = make;
        this.model = model;
        this.buildYear = buildYear;
        this.transmission = transmission;
        this.driveType = driveType;
        this.engine = engine;
        this.fuelType = fuelType;
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
     * @return the make
     */
    public String getMake() {
        return make;
    }

    /**
     * @param make the make to set
     */
    public void setMake(String make) {
        this.make = make;
    }

    /**
     * @return the model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the year
     */
    public int getBuildYear() {
        return buildYear;
    }

    /**
     * @param buildYear the year to set
     */
    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    /**
     * @return the transmission
     */
    public String getTransmission() {
        return transmission;
    }

    /**
     * @param transmission the transmission to set
     */
    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    /**
     * @return the engine
     */
    public String getEngine() {
        return engine;
    }

    /**
     * @param engine the engine to set
     */
    public void setEngine(String engine) {
        this.engine = engine;
    }

    /**
     * @return the fuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @param fuelType the fuelType to set
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * @return the driveType
     */
    public String getDriveType() {
        return driveType;
    }

    /**
     * @param driveType the driveType to set
     */
    public void setDriveType(String driveType) {
        this.driveType = driveType;
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
