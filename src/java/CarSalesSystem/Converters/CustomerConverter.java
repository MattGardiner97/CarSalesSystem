/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CarSalesSystem.Converters;

import CarSalesSystem.Entities.Customer;
import CarSalesSystem.Entities.CustomerOrder;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jackp
 */
@FacesConverter(value = "customerConverter")
public class CustomerConverter implements Converter{
    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        
        System.out.println("HERE!");
        
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Customer) {
            return String.valueOf(((Customer) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(modelValue.getClass() + " is not a valid Warehouse"));
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        System.out.println("HERE2!!!");
        System.out.println("Submitted value= " + submittedValue);
        System.out.println("Submitted value class= " + submittedValue.getClass());
        long id;// = Long.parseLong(submittedValue);
        
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
           // return CustomerController.find(Long.valueOf(submittedValue));
          // System.out.println("id in try= " + id);
          // System.out.println(id);
           TypedQuery<Customer> query = em.createNamedQuery("customerFindById", Customer.class);
           query.setParameter("id", Long.parseLong(submittedValue));
           return query.getResultList();
           
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
        }
    }
    
}
