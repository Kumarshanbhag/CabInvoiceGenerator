public class InvoiceService {
    private static final double MIN_FARE = 5.0;
    private final int COST_PER_TIME = 1;
    private final double Minimum_Cost_Per_Kilometer = 10;
    private RideRepository rideRepository;

    public InvoiceService() {
        this.rideRepository =new RideRepository();
    }

    public double calculateFare(double distance, int time) {
        double totalfare = distance * Minimum_Cost_Per_Kilometer + time * COST_PER_TIME;
        return Math.max(totalfare, MIN_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides) {
        double totalFare = 0;
        for(Ride ride : rides) {
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);

    }

    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId, rides);
    }

    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateFare(rideRepository.getRides(userId));
    }
}
