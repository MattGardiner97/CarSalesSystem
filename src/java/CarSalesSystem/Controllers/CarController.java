
package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.CarEJB;
import CarSalesSystem.Entities.Car;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@ManagedBean
@Named(value = "carController")
@RequestScoped
public class CarController {
    @EJB
    private CarEJB carEJB;
    private List<Car> carList = new ArrayList<Car>();
    
    public CarController() { 
    }
    
    public List<CarSalesSystem.Entities.Car> getCarList() {
        carList = carEJB.getCarList();
        return carList;
    }

    public void setCarList(List<CarSalesSystem.Entities.Car> carList) {
        this.carList = carList;
    }

}