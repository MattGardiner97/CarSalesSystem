//Filename: CustomerOrder.java
//Purpose: Backing bean for handling customer order operations
package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.*;
import CarSalesSystem.Entities.*;

import javax.ejb.EJB;
import javax.faces.bean.*;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private List<Customer> customerList;
    private List<Car> carList;
    private List<CustomerOrder> customerOrderList = new ArrayList<CustomerOrder>();

    public CustomerOrderController() {
    }

    // Public Methods           
    public String createCustomerOrder() {
        //Get the requested car from EJB
        Car requestedCar = carEJB.findByID(customerOrder.getOrderedCar().getId());
        //Get the requesting customer from EJB
        Customer requestingCustomer = customerEJB.findByID(customerOrder.getCustomer().getId());
        
        //Add error message if order quantity is less than zero or more than cars available
        if (customerOrder.getQuantity() > requestedCar.getQuantity()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "There are not enough vehicles in stock to fulfill your order.", null));
            return null;
        } else if (customerOrder.getQuantity() <= 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Order quantity must be greater than 0.", null));
            return null;
        }
        
        //Create the order in the EJB
        customerOrder = customerOrderEJB.createCustomerOrder(customerOrder);
        //Add success message
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sucessfully created the order for ".concat(requestingCustomer.getName()), null));

        //Redirect to customer order list
        return "/CustomerOrder/orderList.xhtml";
    }

    public String performSearch() {
        //Redirect to search results page
        return "/CustomerOrder/searchResults.xhtml";
    }
    
    //Helper function for returning a link based on the car subtype
    public String getCarLinkString(Car Target){
        if(Target instanceof NewCar)
            return "/NewCar/searchResults.xhtml";
        else
            return "/UsedCar/searchResults.xhtml";
    }

    //createOrder page load event
    public void createOrder_PageLoad() {
        this.customerList = customerEJB.getCustomerList();
        this.carList = carEJB.getCarList();
    }

    //orderList page load event
    public void orderList_PageLoad() {
        this.customerOrderList = customerOrderEJB.getAllCustomerOrders();
        if (this.customerOrderList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No orders found.", null));

        }
    }

    public String deleteOrder(long orderID) {
        //Delete from EJB
        this.customerOrderEJB.deleteByID(orderID);
        
        //Redirect to order list page
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
