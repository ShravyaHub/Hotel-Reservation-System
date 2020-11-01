import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;

public class HotelReservationTest {

    @Test
    public void givenDates_WhenRegularCustomer_Should_ReturnCheapestHotel() throws ParseException {
        HotelReservation reservation = new HotelReservation();
        String hotelName = reservation.getCheapestHotelAndRate("10Sep2020","11Sep2020");
        Assert.assertEquals("Lakewood, $220", hotelName);
    }

}
