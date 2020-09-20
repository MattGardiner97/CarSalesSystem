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
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid ID " + modelValue.getClass().toString()));
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        System.out.println(submittedValue);
        
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            Customer result = new Customer();
            result.setId(Long.valueOf(submittedValue));
            return result;
           
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
        }
    }
    
}
