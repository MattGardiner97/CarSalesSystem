//File: CustomerOrder.java
//Author: Jack Pashley - 12002954
//Last modified: 16/9/2020
//Purpose: Class representing a customer order entity

package CarSalesSystem.Controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import CarSalesSystem.Entities.CustomerOrder;
import CarSalesSystem.EJB.CustomerOrderEJB;

@ManagedBean
@Named(value="customerController")
@RequestScoped
public class CustomerOrderController {
    
    // Attributes             
    @EJB
    private CustomerOrderEJB customerOrderEJB;
    private CustomerOrder customerOrder = new CustomerOrder();
    private String search = "";
    private List<CustomerOrder> searchResults = new ArrayList<CustomerOrder>();
    private List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
    
    public CustomerOrderController(){}

    // Public Methods           
    public String createCustomerOrder() {
        customerOrder = customerOrderEJB.createCustomerOrder(customerOrder);
        customerOrderList = customerOrderEJB.findCustomerOrder();
        return "customerOrderList.xhtml";
    }

    //Getters & Setters
    public CarSalesSystem.Entities.CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CarSalesSystem.Entities.CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<CarSalesSystem.Entities.CustomerOrder> getCustomerOrderList() {
        customerOrderList = customerOrderEJB.findCustomerOrder();
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CarSalesSystem.Entities.Customer> customerList) {
        this.customerOrderList = customerOrderList;
    }
    
    public String getSearch(){
        return search;
    }
    
    public void setSearch(String search) {
        this.search = search;
    }
    
    public List<CustomerOrder> getSearchResults() {
        return searchResults;
    }
    
    public void setSearchResults(List<CustomerOrder> searchResults) {
        this.searchResults = searchResults;
    }
    
    public void loadSearchResults(){
        this.searchResults = customerOrderEJB.searchByReferenceNumber(this.search);
        if(this.searchResults.isEmpty())
        {
            FacesContext.getCurrentInstance().addMessage(search, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No customer results found: ".concat(search),null));
        }
    }
    
    public String performSearch(){
        return "/CustomerOrder/searchResults.xhtml";
    }
    
}
