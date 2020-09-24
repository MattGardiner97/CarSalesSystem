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
@Named(value = "customerOrderController")
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
    private String details = "";
    private List<Customer> customerList;
    private List<Car> carList;
    private List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();

    public CustomerOrderController() {
    }

    // Public Methods           
    public String createCustomerOrder() {
        Car requestedCar = carEJB.findByID(customerOrder.getOrderedCar().getId());
        Customer requestingCustomer = customerEJB.findByID(customerOrder.getCustomer().getId());
        if (customerOrder.getQuantity() > requestedCar.getQuantity()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There are not enough vehicles in stock to fulfill your order.", null));
            return null;
        } else if (customerOrder.getQuantity() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order quantity must be greater than 0.", null));
            return null;
        }
        customerOrder = customerOrderEJB.createCustomerOrder(customerOrder);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucessfully created the order for ".concat(requestingCustomer.getName()), null));

        return "/CustomerOrder/orderList.xhtml";
    }

    public void loadDetails() {

    }

    public String performSearch() {
        return "/CustomerOrder/searchResults.xhtml";
    }
    
    public String getCarLinkString(Car Target){
        if(Target instanceof NewCar)
            return "/NewCar/searchResults.xhtml";
        else
            return "/UsedCar/searchResults.xhtml";
    }

    public void createOrder_PageLoad() {
        this.customerList = customerEJB.getCustomerList();
        this.carList = carEJB.getCarList();
    }

    public void orderList_PageLoad() {
        this.customerOrderList = customerOrderEJB.getAllCustomerOrders();
        if (this.customerOrderList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No orders found.", null));

        }
    }

    public String deleteOrder(long orderID) {
        this.customerOrderEJB.deleteByID(orderID);
        return "/CustomerOrder/orderList.xhtml";
    }

    //Getters & Setters
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public List<CustomerOrder> getCustomerOrderList() {
        return customerOrderList;
    }

    public void setCustomerOrderList(List<CustomerOrder> customerOrderList) {
        this.customerOrderList = customerOrderList;
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
