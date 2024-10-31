public class Car extends Vehicle {
    private String model;
    public Car(String licensePlate, String colour, double pricePerDay, String model) {
        super(licensePlate, colour, pricePerDay);
        this.model = model;
    }

    // Getters and setters
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public double calculateDailyRentalCost() {
        return super.getPricePerDay() + 1.3 * getPricePerDay();
    }

}
