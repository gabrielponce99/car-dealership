package com.pluralsight;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner = new Scanner(System.in);
    private Dealership dealership;
    private DealershipFileManager fileManager = new DealershipFileManager();

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

            System.out.println("Enter the command: ");
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
                    System.out.println("Thanks for using our app!");
                    System.exit(0);
                }
            }
        }
    }

    public void processGetByPriceRequest() {
        System.out.println("Enter minimum price: ");
        BigDecimal min = scanner.nextBigDecimal();
        scanner.nextLine();

        System.out.println("Enter maximum price: ");
        BigDecimal max = scanner.nextBigDecimal();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    public void processGetByMakeModelRequest() {
        System.out.println("Enter make (or leave blank): ");
        String make = scanner.nextLine();

        System.out.println("Enter model (or leave blank): ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));

    }

    public void processGetByYearRequest() {
        System.out.println("Enter the oldest year (e.g. 1999): ");
        int min = scanner.nextInt();

        System.out.println("Enter the newest year (e.g. 2026): ");
        int max = scanner.nextInt();

        displayVehicles(dealership.getVehiclesByYear(min, max));

    }

    public void processGetByColorRequest() {
        System.out.println("Enter the color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    public void processGetByMileageRequest() {
        System.out.println("Enter minimum mileage: ");
        int min = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter maximum mileage: ");
        int max = scanner.nextInt();
        scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("Enter the vehicle type: ");
        String vehicleType = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(vehicleType));
    }

    public void processGetAllVehiclesRequest() {
        List<Vehicle> vehicles = this.dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {
        int vin = promptForInt("Enter VIN: ");
        int year = promptForInt("Enter year: ");
        String make = promptForString("Enter make: ");
        String model = promptForString("Enter model: ");
        String type = promptForString("Enter vehicle type (e.g. SUV, car, truck, van): ");
        String color = promptForString("Enter color: ");
        int odometer = promptForInt("Enter odometer: ");
        BigDecimal price = promptForBigDecimal("Enter price: ");

        Vehicle vehicle = new Vehicle(vin, year, odometer, make, model, color, type, price);
        dealership.addVehicle(vehicle);

        fileManager.saveDealership(dealership);
        System.out.println("Vehicle added successfully!");
    }

    public void processRemoveVehicleRequest() {
        int vin = promptForInt("Enter the VIN of the vehicle to remove: ");

        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
            }
        }
        if (vehicleToRemove == null) {
            System.out.println("No vehicle found with VIN " + vin + ".");
            return;
        }

        dealership.removeVehicle(vehicleToRemove);

        fileManager.saveDealership(dealership);
        System.out.println("Vehicle with VIN " + vin + " removed successfully!.");
    }

    private BigDecimal promptForBigDecimal(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("This can't be blank.");
                continue;
            }

            try {
                BigDecimal value = new BigDecimal(input);

                if (value.compareTo(BigDecimal.ZERO) < 0) {
                    System.out.println("Please enter a positive amount.");
                } else {
                    return value;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number (e.g. 15999.99).");
            }
        }
    }

    private int promptForInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number (e.g. 89).");
            }
        }
    }

    public String promptForString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("This can't be blank.");
            } else if (input.contains("|")) {
                System.out.println("Please don't use the | character.");
            } else {
                return input;
            }
        }
    }
}
