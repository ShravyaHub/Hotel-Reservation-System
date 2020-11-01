import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class HotelReservation {

    ArrayList<HotelDetails> HotelList = new ArrayList<>();
    ArrayList<String> cheapestHotelNameList;
    HashMap<String, Integer> hotelRatingMap;
    HashMap<String, Long> hotelRateMap;
    Long minRate;

    public void printWelcomeMessage() {
        System.out.println("Welcome to Hotel Reservation System");
    }

    public void addHotelDetails(String hotelName, int weekDayRate, int weekendRate, int rating, int rewardCutomerWeekdayRates, int rewardCustomerWeekendRates) {
        HotelDetails hotelDetails = new HotelDetails(hotelName, weekDayRate, weekendRate, rating, rewardCutomerWeekdayRates, rewardCustomerWeekendRates);
        HotelList.add(hotelDetails);
    }

    public static long calculateRewardCost(HotelDetails hotelDetails, String arrival, String checkout, boolean rewardCustomer) {
        LocalDate start = convertStringToDate(arrival);
        LocalDate end = convertStringToDate(checkout);
        long totalCost = 0;
        end = end.plusDays(1);
        while (!(start.equals(end))) {

            int day = start.getDayOfWeek().getValue();

            if (day == 6 || day == 7){
                if(rewardCustomer) {
                    totalCost = totalCost + hotelDetails.getRewardCustomerWeekendrate();
                } else {
                    totalCost = totalCost + hotelDetails.getWeekendRate();
                }
            }
            else{
                if(rewardCustomer) {
                    totalCost = totalCost + hotelDetails.getRewardCustomerWeekdayRate();
                } else {
                    totalCost = totalCost + hotelDetails.getWeekdayRate();
                }
            }
            start = start.plusDays(1);

        }
        return totalCost;
    }

    public ArrayList<String> findCheapestHotel(String arrival, String checkout, boolean rewardCustomer) {
        addHotelDetails("Lakewood",110,90, 3, 80, 80);
        addHotelDetails("Bridgewood",150, 50, 4, 110, 50);
        addHotelDetails("Ridgewood",220, 150, 5, 100, 40);
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        cheapestHotelNameList = new ArrayList<>();
        hotelRatingMap = new HashMap<>();
        hotelRateMap = new HashMap<String, Long>();
        minRate = HotelList.stream().map(h -> calculateRewardCost(h, arrival, checkout, rewardCustomer)).min(Long::compare).get();
        for (HotelDetails hotelDetails : HotelList) {
            LocalDate start = arrivalDate;
            LocalDate end = checkoutDate.plusDays(1);
            Long hotelRent = Long.valueOf(0);
            while (!(start.equals(end))) {

                int day = start.getDayOfWeek().getValue();

                if (day == 6 || day == 7){
                    if(rewardCustomer) {
                        hotelRent = hotelRent + hotelDetails.getRewardCustomerWeekendrate();
                    } else {
                        hotelRent = hotelRent + hotelDetails.getWeekendRate();
                    }
                }
                else{
                    if(rewardCustomer) {
                        hotelRent = hotelRent + hotelDetails.getRewardCustomerWeekdayRate();
                    } else {
                        hotelRent = hotelRent + hotelDetails.getWeekdayRate();
                    }
                }
                start = start.plusDays(1);
            }
            if (hotelRent <= minRate) {
                minRate = hotelRent;
                hotelRateMap.put(hotelDetails.getHotelName(), hotelRent);
                hotelRatingMap.put(hotelDetails.getHotelName(), hotelDetails.getRating());
            }
        }
        for (Map.Entry<String, Long> entry : hotelRateMap.entrySet()) {
            if (entry.getValue().equals(minRate)) {
                cheapestHotelNameList.add(entry.getKey());
            }
        }
        for (String hotel: cheapestHotelNameList){
            System.out.println("Hotel Name: " + hotel + " Total Rate $" + minRate);
        }
        return cheapestHotelNameList;
    }

    public String cheapestBestRatedHotel(String arrival, String checkout, boolean rewardCustomer) {

        findCheapestHotel(arrival, checkout, rewardCustomer);
        List<HotelDetails> cheapestHotelsList = HotelList.stream().filter(hotel -> calculateRewardCost(hotel, arrival, checkout, rewardCustomer) == minRate).collect(Collectors.toList());
        HotelDetails bestRatedHotel = cheapestHotelsList.stream().max(Comparator.comparingInt(HotelDetails::getRating)).get();
        String cheapestHotel = bestRatedHotel.getHotelName();
        int bestRating = bestRatedHotel.getRating();
        System.out.println("Hotel Name: " + cheapestHotel + ", Rating: " + bestRating + " and Total Rate $" + minRate);
        return cheapestHotel;
    }

    public static LocalDate convertStringToDate(String dateString) {
        LocalDate date = null;
        DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("ddMMMyyyy");
        try {
            date = LocalDate.parse(dateString, dateTimeFormat);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public String findBestRatedHotel(String arrival, String checkout, boolean rewardCustomer) {
        addHotelDetails("Lakewood",110,90, 3, 80, 80);
        addHotelDetails("Bridgewood",150, 50, 4, 110, 50);
        addHotelDetails("Ridgewood",220, 150, 5, 100, 40);
        LocalDate arrivalDate = convertStringToDate(arrival);
        LocalDate checkoutDate = convertStringToDate(checkout);
        int totalHotelRent = 0;
        int bestRating = 0;
        HotelDetails BestRatedHotel = null;
        for (HotelDetails hotelDetails : HotelList) {
            LocalDate start = arrivalDate;
            LocalDate end = checkoutDate.plusDays(1);
            int hotelRent = 0;
            while (!(start.equals(end))) {

                int day = start.getDayOfWeek().getValue();

                if (day == 6 || day == 7){
                    if(rewardCustomer) {
                        hotelRent = hotelRent + hotelDetails.getRewardCustomerWeekendrate();
                    } else {
                        hotelRent = hotelRent + hotelDetails.getWeekendRate();
                    }
                }
                else{
                    if(rewardCustomer) {
                        hotelRent = hotelRent + hotelDetails.getRewardCustomerWeekdayRate();
                    } else {
                        hotelRent = hotelRent + hotelDetails.getWeekdayRate();
                    }
                }
                start = start.plusDays(1);
            }
            if(hotelDetails.getRating() > bestRating){
                bestRating = hotelDetails.getRating();
                BestRatedHotel = hotelDetails;
                totalHotelRent = hotelRent;
            }
        }
        System.out.println("Hotel Name: " + BestRatedHotel.getHotelName() + ", Rating: " + bestRating + " and Total Rate $" + totalHotelRent);
        return BestRatedHotel.getHotelName();
    }

    public static void main(String[] args) throws InvalidUserChoiceException {
        System.out.println("Welcome to Hotel Reservation System");
        Scanner scanner = new Scanner(System.in);
        HotelReservation hotelReservation = new HotelReservation();
        while (true) {
            System.out.println("Are you a reward customer(true/false): ");
            boolean rewardCustomer = scanner.nextBoolean();
            scanner.nextLine();
            System.out.println("Enter arrival date: ");
            String arrival = scanner.nextLine();
            System.out.println("Enter the departure date: ");
            String departure = scanner.nextLine();
            System.out.println("Choose: \n 1.Best rated hotel\n 2.Cheapest hotel\n 3.Cheapest Best Rated Hotel");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    hotelReservation.findBestRatedHotel(arrival, departure, rewardCustomer);
                    break;

                case 2:
                    hotelReservation.findCheapestHotel(arrival, departure, rewardCustomer);
                    break;

                case 3:
                    hotelReservation.cheapestBestRatedHotel(arrival, departure, rewardCustomer);
                    break;

                default:
                    throw new InvalidUserChoiceException("Invalid choice");
            }
        }
    }

}
