import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BusTrip {
    //Attributes of the class BusTrip
    final LocalDateTime departureTime;
    final LocalDateTime arriveTime;
    final City departurePoint;
    final City destinationPoint;
    final double price;
    private final Map<Integer, String> seatNumberToUserId;
    final int seatsCount = 60;

    //Constructor
    public BusTrip(LocalDateTime departureTime, LocalDateTime arriveTime, City fromCity, City toCity, double price) { //
        this.departureTime = departureTime;
        this.arriveTime = arriveTime;
        this.departurePoint = fromCity;
        this.destinationPoint = toCity;
        this.price = price;
        seatNumberToUserId = new HashMap<>();
    }

    public Map<Integer, String> getSeatNumberToUserIdMap() {
        return Map.copyOf(seatNumberToUserId);
    }

    //Method to display seats
    public void displaySeats() {
        System.out.println("--------------------");
        for (int i = 1; i <= seatsCount; i = i + 3) {
            if (i <= 9) {
                System.out.println("|-" + i + "--- --- " + (i + 1) + " --- " + (i + 2) + "-|");
            } else {
                System.out.println("|" + i + "--- --- " + (i + 1) + "---" + (i + 2) + "-|");
            }
        }
        System.out.println("--------------------");
    }

    //Method to check if the selected seat is available
    public boolean checkSeat(int seatCode) {
        if (seatCode > seatsCount || seatCode <= 0) {
            System.out.println("No seat exists with that code !!!");
            return false;
        }
        if (seatNumberToUserId.containsKey(seatCode)) {
            System.out.println("This seat is NOT available !!!");
            return false;
        }
        System.out.println("This seat is available !!!");
        return true;
    }

    public void assignSeatToUser(int seatNo, String userId) {
        seatNumberToUserId.put(seatNo, userId);
    }

    // Removes the user from seat, making the seat empty
    public void removeUserOnSeat(int seatNo) {
        seatNumberToUserId.remove(seatNo);
    }

    public String getUserOnSeat(int seatNo) {
        return seatNumberToUserId.get(seatNo);
    }

    // Returns the seat numbers that are assigned to provided userId
    public List<Integer> getSeatsAssignedToUser(String userId) {
        List<Integer> seatsOfUser = new ArrayList<>();
        for (Map.Entry<Integer, String> entry : seatNumberToUserId.entrySet()) {
            if (entry.getValue().equals(userId)) {
                seatsOfUser.add(entry.getKey());
            }
        }
        return seatsOfUser;
    }
}
