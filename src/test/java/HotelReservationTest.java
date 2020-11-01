import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest {

    @BeforeClass
    public static void beforeClass() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.printWelcomeMessage();
    }

    @Test
    public void givenDates_ForRegularCustomer_ShouldReturnCheapestHotels() {
        HotelReservation hotelReservation = new HotelReservation();
        Object[] hotelName = hotelReservation.findCheapestHotel("11Sep2020", "12Sep2020", false).toArray();
        Object[] expectedHotelName = {"Bridgewood","Lakewood"};
        Assert.assertArrayEquals(expectedHotelName, hotelName);
    }

    @Test
    public void givenDates_ForRewardCustomer_ShouldReturnCheapestHotels() {
        HotelReservation hotelReservation = new HotelReservation();
        Object[] hotelName = hotelReservation.findCheapestHotel("11Sep2020", "12Sep2020", true).toArray();
        Object[] expectedHotelName = {"Ridgewood"};
        Assert.assertArrayEquals(expectedHotelName, hotelName);
    }

    @Test
    public void givenDates_ForRegularCustomer_ShouldReturnCheapestBestRatedHotel() {
        HotelReservation hotelReservation = new HotelReservation();
        String hotelName = hotelReservation.cheapestBestRatedHotel("11Sep2020", "12Sep2020", false);
        Assert.assertEquals("Bridgewood", hotelName);
    }

    @Test
    public void givenDates_ForRewardCustomer_ShouldReturnCheapestBestRatedHotel() {
        HotelReservation hotelReservation = new HotelReservation();
        String hotelName = hotelReservation.cheapestBestRatedHotel("11Sep2020", "12Sep2020", true);
        Assert.assertEquals("Ridgewood", hotelName);
    }

    @Test
    public void givenDates_ForRegularCustomer_ShouldReturnBestRatedHotel() {
        HotelReservation hotelReservation = new HotelReservation();
        String hotelName = hotelReservation.findBestRatedHotel("11Sep2020", "12Sep2020", false);
        Assert.assertEquals("Ridgewood", hotelName);
    }

    @Test
    public void givenDates_ForRewardCustomer_ShouldReturnBestRatedHotel() {
        HotelReservation hotelReservation = new HotelReservation();
        String hotelName = hotelReservation.findBestRatedHotel("11Sep2020", "12Sep2020", true);
        Assert.assertEquals("Ridgewood", hotelName);
    }
}