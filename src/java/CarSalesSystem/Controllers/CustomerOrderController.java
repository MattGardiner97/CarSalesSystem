//File: CustomerOrder.java
//Author: Jack Pashley - 12002954
//Last modified: 16/9/2020
//Purpose: Class representing a customer order entity

package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.*;
import CarSalesSystem.Entities.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Named;


@ManagedBean
@Named(value="customerOrderController")
@ViewScoped
public class CustomerOrderController {
    
    // Attributes             
    @EJB
    private CustomerOrderEJB customerOrderEJB;
    @EJB
    private CustomerEJB customerEJB;
    @EJB
    private CarEJB carEJB;
    
    private CustomerOrder customerOrder = new CustomerOrder();
    private long search = 0;
    private String details = "";
    private List<Customer> customerList;
    private List<Car> carList;
    private List<CustomerOrder> searchResults = new ArrayList<CustomerOrder>();
    private List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();
    
    public CustomerOrderController(){}

    // Public Methods           
    public String createCustomerOrder() {
        customerOrder = customerOrderEJB.createCustomerOrder(customerOrder);
        customerOrderList = customerOrderEJB.getAllCustomerOrders();
        return "/CustomerOrder/orderList.xhtml";
    }
    
    public void loadSearchResults() {           
        this.searchResults = customerOrderEJB.searchById(this.search);
        if (this.searchResults.isEmpty()) {
            System.out.println("Load Search Results, search = " + search);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No order results found for: ".concat(Long.toString(search)), null));
        }
    }
    
    public void loadDetails() {
        this.searchResults = customerOrderEJB.searchById(search);
        System.out.println(searchResults);
        if (this.searchResults == null) {
            FacesContext.getCurrentInstance().addMessage(details, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No customer orders found for ID: ".concat(Long.toString(search)), null));
        }
    }
    
    public String performSearch(){
        return "/CustomerOrder/searchResults.xhtml";
    }
    
    public void createOrder_PageLoad(){
        this.customerList = customerEJB.getCustomerList();
        this.carList = carEJB.getCarList();
    }

    //Getters & Setters
    public CarSalesSystem.Entities.CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CarSalesSystem.Entities.CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<CarSalesSystem.Entities.CustomerOrder> getCustomerOrderList() {
        customerOrderList = customerOrderEJB.getAllCustomerOrders();
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CarSalesSystem.Entities.Customer> customerList) {
        this.customerOrderList = customerOrderList;
    }
    
    public long getSearch(){
        return search;
    }
    
    public void setSearch(long search) {
        this.search = search;
    }
    
    public List<CustomerOrder> getSearchResults() {
        return searchResults;
    }
    
    public void setSearchResults(List<CustomerOrder> searchResults) {
        this.searchResults = searchResults;
    }
        
    

    /**
     * @return the customerList
     */
    public List<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * @param customerList the customerList to set
     */
    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    /**
     * @return the carList
     */
    public List<Car> getCarList() {
        return carList;
    }

    /**
     * @param carList the carList to set
     */
    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    
}
