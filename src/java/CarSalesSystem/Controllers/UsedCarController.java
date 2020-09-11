/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.UsedCarEJB;
import CarSalesSystem.Entities.UsedCar;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Matt
 */
@ManagedBean
@Named(value = "usedCarController")
@RequestScoped
public class UsedCarController {
    @EJB
    private UsedCarEJB carEJB;
    private UsedCar car = new UsedCar();
    private List<UsedCar> carList = new ArrayList<UsedCar>();
    private String searchReferenceNumber = "";
    private List<UsedCar> searchResults = new ArrayList<UsedCar>();
    
    
    public UsedCarController() {
        
    }
    
    public String createCar(){
        carEJB.createUsedCar(car);
        return "/index.xhtml";
    }
    
    public void loadCarList(){
        this.carList = carEJB.getCarList();
    }
    
    public String performSearch(){
        this.searchResults = carEJB.searchByReferenceNumber(searchReferenceNumber);
        if(this.searchResults.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(searchReferenceNumber, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No car found for reference number: ".concat(searchReferenceNumber),null));
            return null;
        }
        return "/UsedCar/searchResults.xhtml";
    }
    
    //Getters & Setters
    public UsedCar getCar(){
        return this .car;
    }
    public void setCar(UsedCar car){
        this.car = car;
    }
    
    public List<UsedCar> getCarList(){
        return this.carList;
    }
    public void setCarList(List<UsedCar> carList){
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
    public List<UsedCar> getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(List<UsedCar> searchResults) {
        this.searchResults = searchResults;
    }
    
}
