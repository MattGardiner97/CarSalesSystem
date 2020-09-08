/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CarSalesSystem.Controllers;

import CarSalesSystem.EJB.NewCarEJB;
import CarSalesSystem.Entities.NewCar;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author Matt
 */
@ManagedBean
@Named(value = "newCarController")
@RequestScoped
public class NewCarController {
    @EJB
    private NewCarEJB carEJB;
    private NewCar car = new NewCar();
    
    
    public NewCarController() {
    }
    
    public NewCar getCar(){
        return this .car;
    }
    public void setCar(NewCar car){
        this.car = car;
    }
    
    public String createCar(){
        carEJB.createNewCar(car);
        return "index.xhtml";
    }
    
}
