import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HotelReservationTest {

    @BeforeClass
    public static void beforeClass() {
        HotelReservation hotelReservation = new HotelReservation();
        hotelReservation.printWelcomeMessage();
    }

    @Test
    public void givenThreeHotels_ForGivenDates_ShouldCheapestHotel() {
        HotelReservation hotelReservation = new HotelReservation();
        Object[] hotelName = hotelReservation.findCheapestHotelForRegularCustomer("10Sep2020", "11Sep2020").toArray();
        Object[] expectedHotelName = {"Lakewood"};
        Assert.assertArrayEquals(expectedHotelName,hotelName);
    }

    @Test
    public void givenThreeHotels_ForGivenDates_HavingBothWeekDayAndWeekend_ShouldReturnCheapestHotels() {
        HotelReservation hotelReservation = new HotelReservation();
        Object[] hotelName = hotelReservation.findCheapestHotelForRegularCustomer("11Sep2020", "12Sep2020").toArray();
        Object[] expectedHotelName = {"Lakewood","Bridgewood"};
        Assert.assertArrayEquals(expectedHotelName, hotelName);
    }
}