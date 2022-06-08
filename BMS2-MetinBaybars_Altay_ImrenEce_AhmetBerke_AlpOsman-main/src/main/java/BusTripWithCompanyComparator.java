import java.util.Comparator;

//This class compares selected trips and check their departure times
public class BusTripWithCompanyComparator implements Comparator<BusTripWithCompany> {
    @Override
    public int compare(BusTripWithCompany o1, BusTripWithCompany o2) {
        if (o1.company.equals(o2.company)) {
            return o1.trip.departureTime.compareTo(o2.trip.departureTime);
        }
       return o1.company.compareTo(o2.company);
    }
}
