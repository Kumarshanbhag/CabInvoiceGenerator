public class InvoiceGenerator {
    private static final double MIN_FARE = 5.0;
    private final int COST_PER_TIME = 1;
    private final double Minimum_Cost_Per_Kilometer = 10;

    public double calculateFare(double distance, int time) {
        double totalfare = distance * Minimum_Cost_Per_Kilometer + time * COST_PER_TIME;
        if(totalfare < MIN_FARE) {
            return MIN_FARE;
        }
        return totalfare;
    }
}
