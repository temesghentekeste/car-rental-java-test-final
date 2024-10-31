import java.util.Objects;

public abstract class Vehicle {
    private String licensePlate;
    private String colour;
    private double pricePerDay;
    private boolean isRented;

    public Vehicle(String licensePlate, String colour, double pricePerDay) {
        this.licensePlate = licensePlate;
        this.colour = colour;
        this.pricePerDay = pricePerDay;
    }

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

    public double getPricePerDay() {
        return pricePerDay;
    }

    @Override
    public String toString() {
        return "licensePlate='" + licensePlate + '\'' +
                ", colour='" + colour + '\'' +
                ", pricePerDay=" + pricePerDay +
                ", isRented=" + isRented;
    }

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

    public abstract double calculateDailyRentalCost();

}
