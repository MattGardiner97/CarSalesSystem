package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.CarEJB;
import CarSalesSystem.Entities.NewCar;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Matt
 */
@Named(value = "newCarController")
@RequestScoped
public class NewCarController {
    @EJB
    private CarEJB carEJB;
    private NewCar car;
    
    
    
    public NewCarController() {
    }
    
    
    
}
