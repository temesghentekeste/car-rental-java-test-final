public class Motorcycle extends Vehicle{
    private String brand;

    public Motorcycle(String licensePlate, String colour, double pricePerDay, String brand) {
        super(licensePlate, colour, pricePerDay);
        this.brand = brand;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // Override the getPricePerDay method to include the brand in the price calculation
    @Override
    public double calculateDailyRentalCost() {
        return super.getPricePerDay() + 1.1 * getPricePerDay();
    }
}
