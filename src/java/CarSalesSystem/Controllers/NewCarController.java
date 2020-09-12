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
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
        FacesContext.getCurrentInstance().addMessage(searchReferenceNumber, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created new car: ".concat(car.toString()),null));
        return "/NewCar/listNewCars.xhtml";
    }
    
    public void loadCarList(){
        this.carList = carEJB.getCarList();
    }
    public void loadSearchResults(){
        this.searchResults = carEJB.searchByReferenceNumber(this.searchReferenceNumber);
        if(this.searchResults.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(searchReferenceNumber, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No car found for reference number: ".concat(searchReferenceNumber),null));
        }
    }
    
    public String performSearch(){
        return "/NewCar/searchResults.xhtml";
    }
    
//    public String performSearch(){
//        return performSearch(this.searchReferenceNumber);
//    }
//    public String performSearch(String referenceNumber){
//        this.searchResults = carEJB.searchByReferenceNumber(referenceNumber);
//        if(this.searchResults.isEmpty())
//        {
//            FacesContext.getCurrentInstance().addMessage(searchReferenceNumber, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No car found for reference number: ".concat(searchReferenceNumber),null));
//            return null;
//        }
//        return "/NewCar/searchResults.xhtml";
//    }
    
    //Getters & Setters
    public NewCar getCar(){
        return this.car;
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
