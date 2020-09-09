package CarSalesSystem.Controllers;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@RequestScoped
public class CustomerController {

    // Attributes             
    @EJB
    private CarSalesSystem.EJB.CustomerEJB customerEJB;
    private CarSalesSystem.Entities.Customer customer = new CarSalesSystem.Entities.Customer();
    private List<CarSalesSystem.Entities.Customer> customerList = new ArrayList<CarSalesSystem.Entities.Customer>();

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
}