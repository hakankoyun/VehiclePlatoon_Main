package com.example.demo.Vehicle.Verbindung;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class Controller {

    Vehicle vehicle = new Vehicle(1,2,3,80,true);
    VehicleSrv vehicleSrv = new VehicleSrv();



    @GetMapping(path="/addVehicle/{position}")
    public @ResponseBody
    Vehicle addVehicle(@PathVariable int position){

        return  vehicleSrv.addVehicle(position);
    }

    @RequestMapping(path="/getPlatoon")
    public List<Vehicle> getPlatoon(){
        return vehicleSrv.getVehicleList();
    }

    @RequestMapping(path ="/showVehicleAgentById/{id}")
    public @ResponseBody
    Vehicle show(){
        return vehicleSrv.getOneVehicleById(vehicle.getId());
    }


    @RequestMapping(path = "/stop")
    public @ResponseBody
    String stop(){
        vehicleSrv.stop();
        return "STOP";
    }



    @GetMapping(path= "/Verbunden/{id}")
    public String socketDa(@PathVariable int id){
        vehicleSrv.verbunden(id);
        return "Verbunden";
    }

    @RequestMapping(path = "/exit/{id}")
    public String exit(@PathVariable int id){
        vehicleSrv.verlassePlatoon(id);
        return "Erfolgreich Vehicle gelöscht";
    }

    @RequestMapping(path ="/slow/{vehicleSpeed}" )
    public String slow(@PathVariable int vehicleSpeed){
        vehicleSrv.langsamm (vehicleSpeed);
        return "Geschwindigkeit Reduziert";
    }

    @RequestMapping(path = "/accelerate/{vehicleSpeed}")
    public String accelerate(@PathVariable int vehicleSpeed){
        vehicleSrv.accelerate(vehicleSpeed);
        return "Beschleunigen";
    }
}
