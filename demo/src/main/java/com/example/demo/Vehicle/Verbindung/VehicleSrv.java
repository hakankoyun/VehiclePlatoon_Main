package com.example.demo.Vehicle.Verbindung;

import com.example.demo.Vehicle.VehicleModel.VehicleRepo;
import java.util.List;

public class VehicleSrv {

    VehicleRepo vehicleRep = new VehicleRepo();
    Suser leader;

    public List<Vehicle> getVehicleList() {
        return vehicleRep.getVehicleList();
    }

    public Vehicle addVehicle(int position) {
        var vehicle = vehicleRep.addvehicle(position);
        return vehicle;
    }

    // Bremsen
    public void langsamm(int spd) {

        int newSpd;
        int currentS = vehicleRep.getSpd();
        if (currentS != -1) {
            if (currentS - spd <= 0) {
                newSpd = 0;
            } else {
                newSpd = currentS - spd;
            }
            vehicleRep.setSpd(newSpd);
            leader.sendMsg("Jetzige Geschwindigkeit: " + newSpd);
        }

    }

    // Vehicle stoppen
    public void stop() {

        vehicleRep.setSpd(0);
        if (leader != null) {
            leader.sendMsg("Jetzige Geschwindigkeit: " + 0);
        }

    }

    // Beschleunigen
    public void accelerate(int spd) {

        int newSpd;
        int currentS = vehicleRep.getSpd();
        if (currentS != -1) {
            if (currentS + spd >= 100) {
                newSpd = 100;
            } else {

                newSpd = currentS + spd;
            }
            vehicleRep.setSpd(newSpd);
            leader.sendMsg("Jetzige Geschwindigkeit: " + newSpd);
        }

    }


    public void verlassePlatoon(int id) {
        vehicleRep.exit(id);
    }

    public void verbunden(int id) {
        if (id == vehicleRep.getVehicleLeaderId()) {
            leader = new Suser(vehicleRep.getVehicleLeaderId(), 1);
        }
    }

    // Auswahl von einem Truck über ID
    public Vehicle getOneVehicleById(int id) {
        return vehicleRep.getOneVehicleById();
    }


    // Auswahl aller Trucks über ID
    public Vehicle getVehicleById(int id) {
        return vehicleRep.getVehicleById();
    }
}



