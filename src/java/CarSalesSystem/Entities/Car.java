//File: Car.java
//Author: Matthew Gardiner - s0270923
//Last modified: 14/8/2020
//Purpose: Abstract entity class representing a vehicle.
package CarSalesSystem.Entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Entity
@NamedQueries({
    @NamedQuery(name = "carFindAll", query = "SELECT c FROM Car c"), //Retrieves all cars from the database
    @NamedQuery(name = "carFindByReferenceNumber", query = "SELECT c FROM Car c WHERE c.referenceNumber = :referenceNumber"), //Find a car by reference number
    @NamedQuery(name = "carFindByID", query = "SELECT c FROM Car c WHERE c.id = :id")
})
@Inheritance(strategy = InheritanceType.JOINED)
@Named(value = "Car")
@SessionScoped
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false,unique = true)
    private String referenceNumber;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String driveType;

    @Column(nullable = false)
    private String colour;

    @Column(nullable = false)
    private String transmission;

    @Column(nullable = false)
    private String engine;

    @Column(nullable = false)
    private String fuelType;
    
    @Column(nullable = false)
    private int doors;
    
    @Column(nullable = false)
    private int seats;
    
    @Column(nullable = false)
    private int price;

    //Default constructor
    public Car() {
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
     * @return the colour
     */
    public String getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(String colour) {
        this.colour = colour;
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
     * @return the doors
     */
    public int getDoors() {
        return doors;
    }

    /**
     * @param doors the doors to set
     */
    public void setDoors(int doors) {
        this.doors = doors;
    }

    /**
     * @return the seats
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats the seats to set
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * @return the price
     */
    public int getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(int price) {
        this.price = price;
    }
    
     @Override	
    public String toString(){	
        return String.format("%s %s", this.make,this.model);	
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
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
        final Car other = (Car) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
