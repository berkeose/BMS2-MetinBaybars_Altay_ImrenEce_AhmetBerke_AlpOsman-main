import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookServiceMenu {
    public static void run() {
        LocalDate selectedDate = selectDate();
        City departurePoint = selectDeparturePoint();
        City arrivePoint = selectArrivePoint(departurePoint);
        TripManager tripManager = new TripManager();
        List<BusTripWithCompany> filteredTrips = tripManager.getBusTripsByDay(selectedDate, departurePoint, arrivePoint);
        MenuResultAction tripSelectionResult = askUserToSelectTrip(filteredTrips, selectedDate);
        if (tripSelectionResult == MenuResultAction.returnToPreviousMenu) {
            run();
        }
    }
    //Method to select the departure point
    static City selectDeparturePoint() {
        List<City> cities = AllCities.getInstance().getCities();
        return selectCityAmong(cities, "departure point");
    }
    //Method to select the city among the list
    static City selectCityAmong(List<City> cities, String selectionName) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("Please select your " + selectionName);
            for (int i = 1; i <= cities.size(); i++) {
                System.out.println(i + ") " + cities.get(i - 1));
            }
            int selection = scanner.nextInt() - 1;
            try {
                return cities.get(selection);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Select one of the cities shown");
            }
        }
    }
        //Method to select the arrival point
    static City selectArrivePoint(City departurePoint) {
        List<City> cities = AllCities.getInstance().getCities();
        cities.remove(departurePoint);
        return selectCityAmong(cities, "arrive point");
    }
        //Method to select the date of the trip
    static LocalDate selectDate() {
        Scanner scanner = new Scanner(System.in);
        LocalDate now = LocalDate.now();
        List<LocalDate> datesAvailable = new ArrayList<>();
        datesAvailable.add(now);
        datesAvailable.add(now.plusDays(1));
        datesAvailable.add(now.plusDays(2));
        datesAvailable.add(now.plusDays(3));
        while (true) {
            System.out.println("Please select the day: ");
            for (int i = 1; i <= datesAvailable.size(); i++) {
                System.out.println(i + ") " + DateUtilities.formatDateAsDay(datesAvailable.get(i - 1)));
            }
            int selection = scanner.nextInt() - 1;
            try {
                return datesAvailable.get(selection);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Select one of the dates shown");
            }
        }
    }
        //Method to ask user to select a trip
    static MenuResultAction askUserToSelectTrip(List<BusTripWithCompany> trips, LocalDate selectedDay) {
        System.out.println(DateUtilities.formatDateAsDay(selectedDay));
        System.out.println("Select one of the trips");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            for (int i = 1; i <= trips.size(); i++) {
                System.out.println("---------------------------------------------------");
                BusTripWithCompany trip = trips.get(i - 1);
                System.out.println(i + ") " + trip.getInfoToDisplay());
            }
            System.out.println("------------------------------------");
            System.out.println(trips.size() + 1 + ") Return to previous menu");
            System.out.println(trips.size() + 2 + ") Return to main menu");
            int selection = scanner.nextInt();
            if (selection <= trips.size() && selection > 0) {
               MenuResultAction result = BusSeatsMenu.run(trips.get(selection - 1));
               if (result == MenuResultAction.returnToMainMenu) {
                   return result;
               }
            } else if (selection == trips.size() + 1) {
                return MenuResultAction.returnToPreviousMenu;
            } else if (selection == trips.size() + 2) {
                return MenuResultAction.returnToMainMenu;
            } else {
                System.out.println("Enter a valid selection");
            }
        }
    }
}
