import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Represents a company that contains its bus trips
abstract public class Company {
    //Attributes of the abstract Company class
    protected List<BusTrip> trips;
    final String name;
    //Constructor
    public Company(String name) {
        this.name = name;
        trips = new ArrayList<>();
    }
    //Method to add trip to company
    public void addTrip(BusTrip trip) {
        trips.add(trip);
    }

    // Comparing them based on names
    public int compareTo(Company other) {
        return name.compareTo(other.name);
    }

    // Equals if inherits from Company and contains the same name
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!Company.class.isAssignableFrom(o.getClass())) return false;
        Company company = (Company) o;
        return name.equals(company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
    //PamukKale child class which inherits the main class
class PamukKale extends Company {
    public PamukKale() {
        super("Pamukkale");
    }
}
    //PamukKale child class which inherits the main class
class Metro extends Company {
    public Metro() {
        super("Metro");
    }
}
    //PamukKale child class which inherits the main class
class Atasoy extends Company {
    public Atasoy() {
        super("Atasoy");
    }
}
    //PamukKale child class which inherits the main class
class KamilKoc extends Company {
    public KamilKoc() {
        super("Kamil Koç");
    }
}
