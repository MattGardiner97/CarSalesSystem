package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.CarEJB;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import CarSalesSystem.Entities.NewCar;

/**
 *
 * @author Matt
 */
@ManagedBean
@Named(value = "newCarController")
@RequestScoped
public class NewCarController {

    @EJB
    private CarEJB carEJB;
    private NewCar car = new NewCar();
    private List<NewCar> carList = new ArrayList<NewCar>();
    private String searchReferenceNumber = "";
    private List<NewCar> searchResults = new ArrayList<NewCar>();

    public NewCarController() {

    }

    public String createCar() {
        carEJB.createCar(car);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created new car: ".concat(car.toString()), null));
        return "/NewCar/listNewCars.xhtml";
    }

    public void loadCarList() {
        this.carList = carEJB.getNewCarList();
        if (this.carList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No new cars found ", null));
        }
    }

    public void loadSearchResults() {
        this.searchResults = carEJB.searchNewCarByReferenceNumber(this.searchReferenceNumber);
        if (this.searchResults.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No car found for reference number: ".concat(searchReferenceNumber), null));
        }
    }

    public String performSearch() {
        return "/NewCar/searchResults.xhtml";
    }

    //Getters & Setters
    public NewCar getCar() {
        return this.car;
    }

    public void setCar(NewCar car) {
        this.car = car;
    }

    public List<NewCar> getCarList() {
        return this.carList;
    }

    public void setCarList(List<NewCar> carList) {
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
