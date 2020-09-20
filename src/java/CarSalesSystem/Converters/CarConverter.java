/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package CarSalesSystem.Converters;

import CarSalesSystem.Entities.Car;
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
@FacesConverter(forClass = Car.class)
@Named(value = "carConverter")
public class CarConverter implements Converter{
    @PersistenceContext(unitName = "CarSalesSystemPU")
    private EntityManager em;
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof Car) {
            return String.valueOf(((Car) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid Warehouse"));
        }
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
           // return CustomerController.find(Long.valueOf(submittedValue));
           TypedQuery<Car> query = em.createNamedQuery("CustomerFindById", Car.class);
           query.setParameter("id", submittedValue);
           return query.getResultList();
           
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Warehouse ID"), e);
        }
    }
    
}
