public class Ride {
    public final double distance;
    public final int time;
    public Category category;

    public Ride(double distance, int time, Category category) {
        this.distance = distance;
        this.time = time;
        this.category = category;
    }
}
