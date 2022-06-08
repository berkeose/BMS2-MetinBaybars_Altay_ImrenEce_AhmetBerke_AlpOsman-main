import java.util.ArrayList;
import java.util.List;

public class AllCities {

    //Members of class initialized
    private final List<City> cities;
    private static AllCities instance;

    //Returns a list of current cities
    public List<City> getCities() {
       return new ArrayList<>(cities);
    }

    //Returns an instance of AllCities
    public static AllCities getInstance() {
        if (instance == null) {
            instance = new AllCities();
        }
        return instance;
    }

    //Adds new cities to cities List
    private AllCities() {
        cities = new ArrayList<>();
        cities.add(new City("İstanbul"));
        cities.add(new City("Ankara"));
    }
}
