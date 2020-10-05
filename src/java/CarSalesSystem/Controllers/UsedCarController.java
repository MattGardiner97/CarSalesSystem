//Filename: UsedCarController.java
//Purpose: Backing bean for UsedCar operations
package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.CarEJB;
import CarSalesSystem.Entities.UsedCar;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@Named(value = "usedCarController")
@RequestScoped
public class UsedCarController {

    //Attributes
    @EJB
    private CarEJB carEJB;
    private UsedCar car = new UsedCar();
    private List<UsedCar> carList = new ArrayList<UsedCar>();
    private String searchReferenceNumber = "";
    private List<UsedCar> searchResults = new ArrayList<UsedCar>();

    public UsedCarController() {

    }

    public String createCar() {
        //Set the quantity to 1
        car.setQuantity(1);
        
        //Create the car using the EJB
        carEJB.createCar(car);
        
        //Add success message
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created used car: ".concat(car.toString()), null));
        
        //Redirect to used car list
        return "/UsedCar/listUsedCars.xhtml";
    }

    public void loadCarList() {
        //Get car list from EJB
        this.carList = carEJB.getUsedCarList();
        
        //Add error message if car list is empty
        if (this.carList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No used cars found ", null));
        }
    }

    public void loadSearchResults() {
        //Get search results from EJB
        this.searchResults = carEJB.searchUsedCarByReferenceNumber(this.searchReferenceNumber);
        
        //Add error message if search results are empty
        if (this.searchResults.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No car found for reference number: ".concat(searchReferenceNumber), null));
        }
    }

    public String performSearch() {
        //Redirect to search results page
        return "/UsedCar/searchResults.xhtml";
    }

    //Getters & Setters
    public UsedCar getCar() {
        return this.car;
    }

    public void setCar(UsedCar car) {
        this.car = car;
    }

    public List<UsedCar> getCarList() {
        return this.carList;
    }

    public void setCarList(List<UsedCar> carList) {
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
