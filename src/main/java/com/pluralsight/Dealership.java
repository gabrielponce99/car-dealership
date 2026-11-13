package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private String name;
    private String address;
    private String phone;
    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<Vehicle>();
    }

    public Dealership() {
        this.inventory = new ArrayList<Vehicle>();
    }

    public List<Vehicle> getVehiclesByPrice(BigDecimal min, BigDecimal max) {
        List<Vehicle> output = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle != null
                    && vehicle.getPrice().compareTo(min) >= 0
                    && vehicle.getPrice().compareTo(max) <= 0) {
                output.add(vehicle);
            }
        }
        return output;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make, String model) {
        List<Vehicle> output = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle != null && vehicle.getMake().equalsIgnoreCase(make)) {
                output.add(vehicle);
            } else if (vehicle != null && vehicle.getModel().equalsIgnoreCase(model)) {
                output.add(vehicle);
            }
        }
        return output;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max) {
        List<Vehicle> output = new ArrayList<>();

        for (Vehicle vehicle : inventory) {
            if (vehicle != null && vehicle.getYear() >= min && vehicle.getYear() <= max) {
                output.add(vehicle);
            }
        }
        return output;
    }

    public List<Vehicle> getVehiclesByColor(String color) {
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max) {
        return null;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType) {
        return null;
    }

    public void addVehicle(Vehicle vehicle) {
        this.inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        this.inventory.remove(vehicle);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }
}
