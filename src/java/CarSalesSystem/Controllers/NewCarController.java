/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.NewCarEJB;
import CarSalesSystem.Entities.NewCar;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Matt
 */
@ManagedBean
@Named(value = "newCarController")
@RequestScoped
public class NewCarController {
    @EJB
    private NewCarEJB carEJB;
    private NewCar car = new NewCar();
    private List<NewCar> carList = new ArrayList<NewCar>();
    private String searchReferenceNumber = "";
    private List<NewCar> searchResults = new ArrayList<NewCar>();
    
    
    public NewCarController() {
        
    }
    
    public String createCar(){
        carEJB.createNewCar(car);
        return "/index.xhtml";
    }
    
    public void loadCarList(){
        this.carList = carEJB.getCarList();
    }
    
    public String performSearch(){
        this.setSearchResults(carEJB.searchByReferenceNumber(searchReferenceNumber));
        return "/NewCar/searchResults.xhtml";
    }
    
    //Getters & Setters
    public NewCar getCar(){
        return this .car;
    }
    public void setCar(NewCar car){
        this.car = car;
    }
    
    public List<NewCar> getCarList(){
        return this.carList;
    }
    public void setCarList(List<NewCar> carList){
        this.carList = carList;
    }
    
    

    /**
     * @return the searchReferenceNumber
     */
    public String getSearchReferenceNumber() {
        return searchReferenceNumber;
    }

    /**
     * @param searchReferenceNumber the searchReferenceNumber to set
     */
    public void setSearchReferenceNumber(String searchReferenceNumber) {
        this.searchReferenceNumber = searchReferenceNumber;
    }

    /**
     * @return the searchResults
     */
    public List<NewCar> getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(List<NewCar> searchResults) {
        this.searchResults = searchResults;
    }
    
}
