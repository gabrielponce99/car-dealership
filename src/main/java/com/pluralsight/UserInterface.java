package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private  Dealership dealership;

    private void init() {
        DealershipFileManager file = new DealershipFileManager();

        this.dealership = file.getDealership();
    }

    public UserInterface() {

    }

    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            System.out.println("Vehicle not found.");
            return;
        }

        System.out.printf("%-8s %-6s %-12s %-12s %-10s %-10s %-10s %-10s%n",
                "VIN", "Year", "Make", "Model", "Type", "Color", "Odometer", "Price");
        System.out.println("-".repeat(85));

        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-8d %-6d %-12s %-12s %-10s %-10s %-10d $%-10.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        init();

        System.out.println("=== Welcome to the " + dealership.getName() + " inventory management system!\n");

        while(true) {
            System.out.println("""
                    Select a number from the following options: 
                    [1] Find vehicles by price
                    [2] Find vehicles by make / model
                    [3] Find vehicles by year range
                    [4] Find vehicles by color
                    [5] Find vehicles by mileage range
                    [6] Find vehicles by type (car, truck, SUV, van)
                    [7] List ALL vehicles
                    [8] Add a vehicle
                    [9] Remove a vehicle
                    [0] Exit""");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> {
                    System.out.println("Thanks for using out app!");
                    System.exit(0);
                }
            }
        }
    }

    public void processGetByPriceRequest() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetByYearRequest() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetByMileageRequest() {

    }

    public void processGetByVehicleTypeRequest() {

    }

    public void processGetAllVehiclesRequest() {

    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }

}
