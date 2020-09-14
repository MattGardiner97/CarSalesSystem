package CarSalesSystem.Controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import CarSalesSystem.Entities.Customer;
import CarSalesSystem.EJB.CustomerEJB;

@ManagedBean
@Named(value="customerController")
@RequestScoped
public class CustomerController {

    // Attributes             
    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<Customer>();
    private String search = "";
    private List<Customer> searchResults = new ArrayList<Customer>();
    
    public CustomerController(){}

    // Public Methods           
    public String createCustomer() {
        customer = customerEJB.createCustomer(customer);
        customerList = customerEJB.findCustomer();
        return "customerList.xhtml";
    }

    //Getters & Setters
    public CarSalesSystem.Entities.Customer getCustomer() {
        return customer;
    }

    public void setBook(CarSalesSystem.Entities.Customer customer) {
        this.customer = customer;
    }

    public List<CarSalesSystem.Entities.Customer> getCustomerList() {
        customerList = customerEJB.findCustomer();
        return customerList;
    }

    public void setCustomerList(List<CarSalesSystem.Entities.Customer> customerList) {
        this.customerList = customerList;
    }
    
    public String getSearch(){
        return search;
    }
    
    public void setSearch(String search) {
        this.search = search;
    }
    
    public List<Customer> getSearchResults() {
        return searchResults;
    }
    
    public void setSearchResults(List<Customer> searchResults) {
        this.searchResults = searchResults;
    }
    
    public void loadSearchResults(){
        this.searchResults = customerEJB.searchByName(this.search);
        if(this.searchResults.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(search, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No customer results found: ".concat(search),null));
        }
    }
    
    public String performSearch(){
        return "/Customer/searchResults.xhtml";
    }
}