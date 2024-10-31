public class Truck extends Vehicle{
    private double cargoWeight;

    public Truck(String licensePlate, String colour, double pricePerDay) {
        super(licensePlate, colour, pricePerDay);
    }

    public double getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(double cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    @Override
    public String toString() {
        return super.toString() +
                "cargoWeight=" + cargoWeight;
    }

    @Override
    public double calculateDailyRentalCost(int days) {
        return 0;
    }
}
