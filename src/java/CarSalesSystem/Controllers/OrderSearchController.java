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
import CarSalesSystem.EJB.CustomerOrderEJB;
import CarSalesSystem.Entities.CustomerOrder;
import javax.faces.component.UIComponent;
import javax.faces.convert.ConverterException;

@ManagedBean
@Named(value = "orderSearchController")
@RequestScoped
public class OrderSearchController {

    @EJB
    private CustomerOrderEJB orderEJB;
    
    private long searchID = 0;
    private List<CustomerOrder> searchResults = new ArrayList<CustomerOrder>();
    
    public String performSearch(){
        this.searchResults = orderEJB.searchById(this.getSearchID());
        System.out.println(this.searchResults.size());
        if (this.searchResults.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "No order results found for: ".concat(Long.toString(getSearchID())), null));
        }
        return "/CustomerOrder/searchResults.xhtml";
    
    }

    /**
     * Creates a new instance of OrderSearchController
     */
    public OrderSearchController() {
    }


    /**
     * @return the searchResults
     */
    public List<CustomerOrder> getSearchResults() {
        return searchResults;
    }

    /**
     * @param searchResults the searchResults to set
     */
    public void setSearchResults(List<CustomerOrder> searchResults) {
        this.searchResults = searchResults;
    }

    /**
     * @return the searchID
     */
    public Long getSearchID() {
        return searchID;
    }

    /**
     * @param searchID the searchID to set
     */
    public void setSearchID(Long searchID) {
        this.searchID = searchID;
    }
}
