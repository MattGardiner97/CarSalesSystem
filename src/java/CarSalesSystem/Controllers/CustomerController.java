//Filename: CustomerController.java
//Purpose: Backing bean for Customer related operations

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
@Named(value = "customerController")
@RequestScoped
public class CustomerController {

    // Attributes             
    @EJB
    private CustomerEJB customerEJB;
    private Customer customer = new Customer();
    private List<Customer> customerList = new ArrayList<Customer>();
    private String search = "";
    private List<Customer> searchResults = new ArrayList<Customer>();
    private long detailsID = 0;

    public CustomerController() {
    }

    public void loadCustomerList() {
        this.customerList = customerEJB.getCustomerList();
        //Add error message if customer list is empty
        if (this.customerList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No customers found ", null));
        }
    }

    // Public Methods           
    public String createCustomer() {
        //Create a new customer
        customer = customerEJB.createCustomer(customer);
        
        //Add a success message
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Successfully created the customer: ".concat(customer.getName()), null));
        
        //Redirect to customer list page
        return "/Customer/customerList.xhtml";
    }

    public void loadSearchResults() {
        //Search for customer by name
        this.searchResults = customerEJB.searchByName(this.search);
        
        //Add error message if search results are empty
        if (this.searchResults.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No customer results found for: ".concat(search), null));
        }
    }

    public String performSearch() {
        //Redirect to search results page
        return "/Customer/searchResults.xhtml";
    }

    public void loadDetails() {
        //Get a customer by ID
        this.customer = customerEJB.findByID(detailsID);
        
        //Add error message if no customer is found
        if (this.customer == null) {
            FacesContext.getCurrentInstance().addMessage(search, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No customer details found for ID: ".concat(Long.toString(detailsID)), null));
        }
    }

    //Getters and Setters
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(CarSalesSystem.Entities.Customer customer) {
        this.customer = customer;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public String getSearch() {
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

    /**
     * @return the detailsID
     */
    public long getDetailsID() {
        return detailsID;
    }

    /**
     * @param detailsID the detailsID to set
     */
    public void setDetailsID(long detailsID) {
        this.detailsID = detailsID;
    }
}
