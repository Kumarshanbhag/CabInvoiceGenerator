public enum Category {
    PREMIUM(15, 2, 20), NORMAL(10, 1, 5);

    private final double costPerKm;
    private final double costPerMin;
    private final double minFarePerRide;

    Category(double costPerKm, int costPerMin, double minFarePerRide) {
        this.costPerKm = costPerKm;
        this.costPerMin = costPerMin;
        this.minFarePerRide = minFarePerRide;
    }

    public double calCostOfCabRide(Ride ride){
        double rideCost = ride.distance * costPerKm + ride.time * costPerMin;
        return Math.max(rideCost, minFarePerRide);
    }
}
