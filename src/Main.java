import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner scanner = new Scanner(System.in);

    private static final String[] MENU_OPTIONS = {
            // Menu options
            "--- Rental System Menu ---",
            "1. Rent a Vehicle",
            "2. Return a Vehicle",
            "3. View All Rented Vehicles",
            "4. View All Available Vehicles",
            "5. View Rental Prices of Rented Vehicles",
            "0. Exit"
    };

    public static void main(String[] args) {

        // List to store all the vehicles in the rental system
        List<Vehicle> vehicleList = new ArrayList<Vehicle>();
        // Add vehicles to the list of type Truck
        vehicleList.add(new Truck("Truck 1", "Red", 10000, 10000));
        vehicleList.add(new Truck("Truck 2", "Green", 12000, 15000));
        vehicleList.add(new Truck("Truck 3", "Blue", 10000, 10000));
        vehicleList.add(new Truck("Truck 4", "Green", 12000, 15000));
        vehicleList.add(new Truck("Truck 5", "Pink", 10000, 10000));
        // Add vehicles to the list of type Car
        vehicleList.add(new Car("Car 1", "Green", 15000, "Toyota"));
        vehicleList.add(new Car("Car 2", "Blue", 20000, "Mazda"));
        vehicleList.add(new Car("Car 3", "Blue", 20000, "Lexus"));
        vehicleList.add(new Car("Car 4", "Blue", 20000, "Tesla"));
        vehicleList.add(new Car("Car 5", "Blue", 20000, "Ford"));

        // Add vehicles to the list of type Motorcycle
        vehicleList.add(new Motorcycle("Motorcycle 1", "Pink", 500, "Honda"));
        vehicleList.add(new Motorcycle("Motorcycle 2", "Pink", 500, "Jaguar"));
        vehicleList.add(new Motorcycle("Motorcycle 3", "Pink", 500, "Jaguar"));
        vehicleList.add(new Motorcycle("Motorcycle 4", "Pink", 500, "Jaguar"));

        // Create a rental object to manage rentals and vehicle availability
        Rental rental = new Rental(vehicleList);

        // Main menu loop
        while (true) {
            printMenu();
            System.out.print("Enter your choice: ");


            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();  // consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();  // consume invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter customer name: ");
                    String name = scanner.nextLine();
                    Customer customer = new Customer(name, "Address", 30);
                    if (name.trim().isEmpty()) {
                        System.out.println("Invalid customer name. Returning to main menu.");
                        continue;
                    }

                    System.out.println("Select Vehicle Type:");
                    System.out.println("1. Car");
                    System.out.println("2. Truck");
                    System.out.println("3. Motorcycle");
                    System.out.print("Enter your choice: ");
                    int vehicleTypeChoice;
                    try {
                        vehicleTypeChoice = scanner.nextInt();
                        scanner.nextLine();  // consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        continue;
                    }

                    List<Vehicle> filteredVehicles = new ArrayList<>();
                    if (vehicleTypeChoice == 1) {
                        System.out.println("Available Cars For Rental:");
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle instanceof Car && !rental.isVehicleRented(vehicle)) {
                                filteredVehicles.add(vehicle);
                            }
                        }
                    } else if (vehicleTypeChoice == 2) {
                        System.out.println("Available Truck For Rental:");
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle instanceof Truck && !rental.isVehicleRented(vehicle)) {
                                filteredVehicles.add(vehicle);
                            }
                        }
                    } else if (vehicleTypeChoice == 3) {
                        System.out.println("Available Motorcycles For Rental:");
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle instanceof Motorcycle &&!rental.isVehicleRented(vehicle)) {
                                filteredVehicles.add(vehicle);
                            }
                        }
                    }
                    else {
                        System.out.println("Invalid choice. Returning to main menu.");
                        break;
                    }

                    if (filteredVehicles.isEmpty()) {
                        System.out.println("No available vehicles of the selected type.");
                    } else {
                        for (Vehicle vehicle : filteredVehicles) {
                            System.out.println(vehicle);
                        }
                        System.out.print("Select a vehicle to rent by entering the licence plate: ");
                        String plate = scanner.nextLine();
                        Vehicle selectedVehicle = null;
                        for (Vehicle vehicle : filteredVehicles) {
                            if (vehicle.getLicensePlate().equals(plate)) {
                                selectedVehicle = vehicle;
                                break;
                            }
                        }
                        if (selectedVehicle != null) {
                            rental.rentVehicle(selectedVehicle, customer);
                        } else {
                            System.out.println("Invalid licence plate entered.");
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter customer name for vehicle return: ");
                    String returnName = scanner.nextLine();
                    Customer returnCustomer = new Customer(returnName, "Address", 30);
                    System.out.print("Enter licence plate of vehicle to return: ");
                    String returnPlate = scanner.nextLine();
                    Vehicle vehicleToReturn = null;
                    for (Vehicle vehicle : vehicleList) {
                        if (vehicle.getLicensePlate().equals(returnPlate) && rental.isVehicleRented(vehicle)) {
                            vehicleToReturn = vehicle;
                            break;
                        }
                    }
                    if (vehicleToReturn != null) {
                        rental.returnVehicle(vehicleToReturn, returnCustomer);
                    } else {
                        System.out.println("Invalid or unavailable vehicle.");
                    }
                    break;

                case 3:
                    System.out.println("Rented Vehicles:");
                    for (Vehicle vehicle : vehicleList) {
                        if (rental.isVehicleRented(vehicle)) {
                            System.out.println(vehicle + " - Rented by " + rental.getRenter(vehicle));
                        }
                    }
                    break;

                case 4:
                    System.out.println("Select Vehicle Type to View Available Vehicles:");
                    System.out.println("1. Car");
                    System.out.println("2. Truck");
                    System.out.println("3. Motorcycle");
                    System.out.print("Enter your choice: ");
                    int viewTypeChoice;
                    try {
                        viewTypeChoice = scanner.nextInt();
                        scanner.nextLine();  // consume newline
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine();
                        continue;
                    }

                    List<Vehicle> availableVehicles = new ArrayList<>();
                    if (viewTypeChoice == 1) {
                        System.out.println("Available Cars:");
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle instanceof Car && !rental.isVehicleRented(vehicle)) {
                                availableVehicles.add(vehicle);
                            }
                        }
                    } else if (viewTypeChoice == 2) {
                        System.out.println("Available Trucks:");
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle instanceof Truck && !rental.isVehicleRented(vehicle)) {
                                availableVehicles.add(vehicle);
                            }
                        }
                    }else if (viewTypeChoice == 3) {
                        System.out.println("Available Motorcycles:");
                        for (Vehicle vehicle : vehicleList) {
                            if (vehicle instanceof Motorcycle && !rental.isVehicleRented(vehicle)) {
                                availableVehicles.add(vehicle);
                            }
                        }
                    }
                    else {
                        System.out.println("Invalid choice. Returning to main menu.");
                        continue;
                    }

                    if (availableVehicles.isEmpty()) {
                        System.out.println("No available vehicles of the selected type.");
                    } else {
                        for (Vehicle vehicle : availableVehicles) {
                            System.out.println(vehicle);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Rental Prices of Rented Vehicles:");
                    for (Vehicle vehicle : vehicleList) {
                        if (rental.isVehicleRented(vehicle)) {
                            System.out.println(vehicle + " - Daily Rental Price: " + vehicle.calculateDailyRentalCost());
                        }
                    }
                    break;

                case 0:
                    System.out.println("Exiting the rental system. Thank you!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }




    }

    private static void printMenu() {
        for (String option : MENU_OPTIONS) {
            System.out.println(option);
        }
    }
}