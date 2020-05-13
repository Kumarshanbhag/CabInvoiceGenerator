public class InvoiceGenerator {
    private final int COST_PER_TIME = 1;
    private final double Minimum_Cost_Per_Kilometer = 10;

    public double calculateFare(double distance, int time) {
        return distance * Minimum_Cost_Per_Kilometer + time * COST_PER_TIME;
    }
}
