import java.util.Objects;

public abstract class Vehicle {
    // private fields for vehicle
    private String licensePlate;
    private String colour;
    private double pricePerDay;
    private boolean isRented;
    private boolean status;


    // constructor for Vehicle
    public Vehicle(String licensePlate, String colour, double pricePerDay) {
        this.licensePlate = licensePlate;
        this.colour = colour;
        this.pricePerDay = pricePerDay;
    }

    // getters and setters for Vehicle
    public String getLicensePlate() {
        return licensePlate;
    }

    public String getColour() {
        return colour;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean isRented) {
        this.isRented = isRented;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    // override toString()
    @Override
    public String toString() {
        return "licensePlate='" + licensePlate + '\'' +
                ", colour='" + colour + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", isRented=" + isRented;
    }

    // override equals() and hashCode() for Vehicle class equality operator
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Double.compare(pricePerDay, vehicle.pricePerDay) == 0 && isRented == vehicle.isRented && Objects.equals(licensePlate, vehicle.licensePlate) && Objects.equals(colour, vehicle.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(licensePlate, colour, pricePerDay, isRented);
    }

    // calculate total rental price for the customer
    // abstract method for calculating daily rental cost
    public abstract double calculateDailyRentalCost();


}
