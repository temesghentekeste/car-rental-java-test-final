import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Rental {

    // Rental class properties
    private List<Vehicle> availableVehicleList = new ArrayList<>();
    private Map<Customer, Set<Vehicle>> rentedVehicleMap;
    private String startDate;
    private String returnDate;
    private double totalPrice;

    // Constructor, parameterized constructor
    public Rental(List<Vehicle> availableVehicleList) {
        this.availableVehicleList = availableVehicleList;
        this.rentedVehicleMap = new HashMap<>();
    }

    // Method to rent a vehicle
    public void rentVehicle(Vehicle vehicle, Customer customer) {
        if (!availableVehicleList.contains(vehicle)) {
            System.out.println("Vehicle is not available for rent.");
            return;
        }

        if (isVehicleRented(vehicle)) {
            System.out.println("Vehicle already rented.");
            return;
        }

        rentedVehicleMap.computeIfAbsent(customer, k -> new HashSet<>()).add(vehicle);
        vehicle.setStatus(true);
        startDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("Vehicle rented by " + customer.getName() + " on " + startDate);
    }

    // Method to return a rented vehicle
    public void returnVehicle(Vehicle vehicle, Customer customer) {
        if (!isVehicleRented(customer, vehicle)) {
            System.out.println("Vehicle is not currently rented by " + customer.getName());
            return;
        }

        vehicle.setStatus(false);
        returnDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        totalPrice = calculateTotalPrice(vehicle, startDate, returnDate);
        System.out.println("Vehicle returned by " + customer.getName() + " on " + returnDate + ", total price: " + totalPrice);
        rentedVehicleMap.get(customer).remove(vehicle);
    }

    // Calculates the total price
    private double calculateTotalPrice(Vehicle vehicle, String startDate, String returnDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(returnDate, formatter);

        long days = ChronoUnit.DAYS.between(start, end);
        return days * vehicle.calculateDailyRentalCost();
    }

    // print rental details
    public void printRentalDetails(Customer customer) {
        System.out.println("Rental details for " + customer.getName() + ":");
        Set<Vehicle> rentedVehicles = rentedVehicleMap.get(customer);

        if (rentedVehicles == null || rentedVehicles.isEmpty()) {
            System.out.println("No vehicles rented.");
        } else {
            for (Vehicle vehicle : rentedVehicles) {
                System.out.println(vehicle + " - Daily Fee: " + vehicle.calculateDailyRentalCost());
            }
            System.out.println("Total rental price: " + totalPrice);
        }
    }


    // get a list of max last 5 rentals per vehicle
    public List<Vehicle> getMaxLast5RentalsPerVehicle() {
        List<Vehicle> maxLast5RentalsPerVehicle = new ArrayList<>();
        for (Vehicle vehicle : availableVehicleList) {
            int count = 0;
            for (Set<Vehicle> rentedSet : rentedVehicleMap.values()) {
                if (rentedSet.contains(vehicle)) {
                    count++;
                }
            }
            if (count > 5) {
                maxLast5RentalsPerVehicle.add(vehicle);
            }
        }
        return maxLast5RentalsPerVehicle;
    }

    // Checks if the given vehicle is currently rented by any customer
    public boolean isVehicleRented(Vehicle vehicle) {
        for (Set<Vehicle> rentedSet : rentedVehicleMap.values()) {
            if (rentedSet.contains(vehicle)) {
                return true;
            }
        }
        return false;
    }

    // Checks if the given vehicle is currently rented by the given customer,
    // it is overloaded to handle the scenario someone else might have rented the vehicle
    public boolean isVehicleRented(Customer customer, Vehicle vehicle) {
        Set<Vehicle> vehicleSet = null;
        System.out.println(rentedVehicleMap + ": " + rentedVehicleMap.containsKey(customer));
        if (rentedVehicleMap.containsKey(customer)) {
            vehicleSet = rentedVehicleMap.get(customer);
        } else {
            return false;
        }

        if (vehicleSet.contains(vehicle)) {
            return true;
        }
        return false;
    }

    // Get the customer who rented the given vehicle
    public Customer getRenter(Vehicle vehicle) {
        for (Map.Entry<Customer, Set<Vehicle>> entry : rentedVehicleMap.entrySet()) {
            if (entry.getValue().contains(vehicle)) {
                return entry.getKey();
            }
        }
        return null;
    }
}