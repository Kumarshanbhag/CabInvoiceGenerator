import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {
    InvoiceService invoiceService;
    RideRepository rideRepository;

    @Before
    public void setUp() throws Exception {
        invoiceService = new InvoiceService();
        rideRepository = new RideRepository();
        invoiceService.setRideRepository(rideRepository);
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5, Category.NORMAL)};
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(1, 25);
        InvoiceSummary invoiceSummary = invoiceService.calculateFare(rides);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenLessDistanceAndTime_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(0.1, 1, Category.NORMAL)};
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(1, 5);
        InvoiceSummary invoiceSummary = invoiceService.calculateFare(rides);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenMuiltpleNormalRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5, Category.NORMAL),
                        new Ride(0.1, 1, Category.NORMAL)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenMuiltplePremiumRides_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(2.0, 5, Category.PREMIUM),
                        new Ride(0.1, 1, Category.PREMIUM)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndMultipleNormalRides_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5, Category.NORMAL),
                        new Ride(0.1, 1, Category.NORMAL)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary invoiceSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30.0);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserIdAndMultiplePremiumRides_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5, Category.PREMIUM),
                        new Ride(0.1, 1, Category.PREMIUM)
        };
        invoiceService.addRides(userId, rides);
        InvoiceSummary invoiceSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 60.0);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }

    @Test
    public void givenUserIdAndMultipleRides_ShouldReturnInvoiceSummary() {
        String userId = "a@b.com";
        Ride[] rides = {new Ride(2.0, 5, Category.PREMIUM),
                        new Ride(0.1, 1, Category.PREMIUM)
        };
        Ride[] rides2 = {new Ride(2.0, 5, Category.NORMAL),
                new Ride(0.1, 1, Category.NORMAL)
        };
        invoiceService.addRides(userId, rides);
        invoiceService.addRides(userId, rides2);
        InvoiceSummary invoiceSummary = invoiceService.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 90.0);
        Assert.assertEquals(expectedInvoiceSummary, invoiceSummary);
    }


}