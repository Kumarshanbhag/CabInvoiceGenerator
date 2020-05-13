import java.util.*;

public class RideRepository {

    Map<String, ArrayList<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRides(String userId, Ride[] rides) {
        ArrayList<Ride> rideList = this.userRides.get(userId);
        if(rideList == null) {
            this.userRides.put(userId, new ArrayList<Ride>(Arrays.asList(rides)));
        } else {
            Collections.addAll(rideList, rides);
            this.userRides.put(userId, rideList);
        }

    }

    public Ride[] getRides(String userId) {
        return this.userRides.get(userId).toArray(new Ride[0]);
    }
}
