public class Truck extends Vehicle{
    private int cargoWeight;

    public Truck(String licensePlate, String colour, double pricePerDay, int cargoWeight ) {
        super(licensePlate, colour, pricePerDay);
        this.cargoWeight = cargoWeight;
    }

    public int getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(int cargoWeight) {
        this.cargoWeight = cargoWeight;
    }


    public double calculateDailyRentalCost() {
        return super.getPricePerDay() + 1.2 * getPricePerDay();
    }

    @Override
    public String toString() {
        return super.toString() +
                "cargoWeight=" + cargoWeight;
    }


}
