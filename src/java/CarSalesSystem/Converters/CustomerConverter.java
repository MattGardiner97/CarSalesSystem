/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CarSalesSystem.Converters;

import CarSalesSystem.Entities.Customer;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import CarSalesSystem.Controllers.CustomerController;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author jackp
 */
@FacesConverter(forClass=CarSalesSystem.Entities.Customer.class)
public class CustomerConverter {
 private EntityManager em;
    
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Customer) {
            return String.valueOf(((Customer) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid Warehouse"));
        }
    }
    
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
           // return CustomerController.find(Long.valueOf(submittedValue));
           TypedQuery<Customer> query = em.createNamedQuery("CustomerFindById", Customer.class);
           query.setParameter("id", submittedValue);
           return query.getResultList();
           
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
        }
    }
    
}
