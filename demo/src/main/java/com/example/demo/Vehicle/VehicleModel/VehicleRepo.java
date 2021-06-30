package com.example.demo.Vehicle.VehicleModel;

import com.example.demo.Vehicle.Verbindung.Vehicle;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Repository
public class VehicleRepo {

    List<Vehicle> vehicleList = new ArrayList<>();
    int id = 0;



    // Vehicle hinzufügen
    public Vehicle addvehicle(int position){
        id++;
        Vehicle vehicle;

        if(position > vehicleList.size()) {
            position = vehicleList.size();
        }

        if (vehicleList.isEmpty())
        {
            //Erster Vehicle im Platoon
            vehicle = new Vehicle(id, 0, 0, 80, true);
            vehicleList.add(0, vehicle);
        }
        else if(position == vehicleList.size())
        {
            //Letzter Vehicle im Platoon
            vehicle = new Vehicle(id, vehicleList.get(position - 1).getId(),0,80,false);
            vehicleList.add(position, vehicle);
            vehicleList.get(position-1).setBackID(id);
        }
        else if(position == 0)
        {
            // Einer Neuer will auf Leader
            vehicle = new Vehicle(id, 0, vehicleList.get(position).getId(), 80, true);
            vehicleList.add(0, vehicle);
            vehicleList.get(position +1).setFrontID(id);
            vehicleList.get(position+1).setMaster(false);

        }
        else
        {
            vehicle = new Vehicle(id, vehicleList.get(position-1).getId(), vehicleList.get(position).getId(),80,false);
            vehicleList.add(position, vehicle);
            vehicleList.get(position-1).setBackID(id);
            vehicleList.get(position+1).setFrontID(id);
        }
        return vehicle;
    }



    public Vehicle getVehicleById(){
        Vehicle t = null;
        for (int i = 0; i < vehicleList.size(); i++) {
            if(id == vehicleList.get(i).getId()){
                t = vehicleList.get(i);
                break;
            }
        }
        return t;
    }

    public int getVehicleLeaderId(){
        return vehicleList.get(0).getId();
    }
    public Vehicle getOneVehicleById() {
        Vehicle t = null;


        t = vehicleList.get(id);
        return t;
    }

    public int getSpd(){
        if(vehicleList.isEmpty()){
            return -1;
        }else{
            return vehicleList.get(0).getVehicleSpeed();
        }
    }

    public void setSpd(int spd){
        for(int i = 0; i < vehicleList.size(); i++)
        {
            vehicleList.get(i).setVehicleSpeed(spd);
        }
    }

    public void setFrontIDById(int id, int newFrontId)
    {
        for (int i = 0; i < vehicleList.size(); i++) {
            if(id == vehicleList.get(i).getId()){
                vehicleList.get(i).setFrontID(newFrontId);
            }
        }
    }

    public void setBackIDById(int id, int newBackId)
    {
        for (int i = 0; i < vehicleList.size(); i++) {
            if(id == vehicleList.get(i).getId()){
                vehicleList.get(i).setBackID(newBackId);
            }
        }
    }

    public void exit(int id){
        Vehicle vehicle = getVehicleById();
        int frontID = vehicle.getFrontID();
        int backID = vehicle.getBackID();
        if(frontID!=0){
            setBackIDById(frontID, backID);
        }
        if (backID != 0)
        {
            setFrontIDById(backID, frontID);
        }
        if (vehicle.isMaster() && vehicleList.size()>1)
        {
            vehicleList.get(1).setMaster(true);
        }
        vehicleList.remove(vehicle);
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
            }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }


        }
