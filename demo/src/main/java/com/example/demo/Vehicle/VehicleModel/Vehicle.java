package com.example.demo.Vehicle.VehicleModel;

public class Vehicle {
    private int id;
    private int frontID;
    private int backID;
    private int vehicleSpeed;
    private boolean isMaster;


    public Vehicle(int id, int frontID, int backID, int vehicleSpeed, boolean isMaster) {
        this.id = id;
        this.frontID = frontID;
        this.backID = backID;
        this.vehicleSpeed = vehicleSpeed;
        this.isMaster = isMaster;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFrontID() {
        return frontID;
    }

    public void setFrontID(int frontID) {
        this.frontID = frontID;
    }

    public int getBackID() {
        return backID;
    }

    public void setBackID(int backID) {
        this.backID = backID;
    }

    public int getVehicleSpeed() {
        return vehicleSpeed;
    }

    public void setVehicleSpeed(int vehicleSpeed) {
        this.vehicleSpeed = vehicleSpeed;
    }

    public boolean isMaster() {
        return isMaster;
    }

    public void setMaster(boolean master) {
        isMaster = master;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", frontID=" + frontID +
                ", backID=" + backID +
                ", vehicleSpeed=" + vehicleSpeed +
                ", isMaster=" + isMaster +
                '}';
    }
}
