package CarSalesSystem.Converters;

import CarSalesSystem.Entities.Car;
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
@FacesConverter(value = "carConverter")
public class CarConverter implements Converter {

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
            Car result = new Car();
            result.setId(Long.valueOf(submittedValue));
            return result;
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid Car ID"), e);
        }
    }

}
