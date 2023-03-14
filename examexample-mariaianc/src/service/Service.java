package service;

import domain.Route;
import repository.Repository;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    //pt combobox 1 cu source cities
    public ArrayList<String> getAllSourceCities()
    {
        return repo
                .getRoutes()
                .stream()                            //il faci stream
                .map(route -> route.getSource_city())
                .distinct()                          //ca sa nu se repete orasele/
                .collect(Collectors.toCollection(ArrayList::new));//chestia asta face din stream arraylist iar
    }

    //pt comboboxul 2 cu destinatiile in functie de source city
    public ArrayList<String> getAllDestinationCitiesForSourceCity(String sourceCity)
    {
        return repo
                .getRoutes()
                .stream()
                .filter(route -> route.getSource_city().equals(sourceCity))//compari stringuri cu equals ca sa nu dea eroare
                .map(route -> route.getDestination_city())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    //pt lista mare cu toate rutele
    //sorteaza dupa departure time si city
    public ArrayList<Route> getSorted()
    {
        return repo.getRoutes().stream().sorted(
                (a,b)->a.getDestination_city().compareTo(b.getDestination_city())
        ).sorted(
                (a,b)-> a.getDeparture_time().compareTo(b.getDeparture_time())
        ).collect(Collectors.toCollection(ArrayList::new));  //asa se face arraylist inapoi pt ca cu stream le transformi in stream
    }


    //pt a doua lista cu available routes
    public ArrayList<Route>getAllRoutesWithDEPARTUREandDestination(String source,String destination)
    {
        return repo
                .getRoutes()
                .stream()
                .filter(route -> (route.getSource_city().equals(source) && route.getDestination_city().equals(destination)))
                .collect(Collectors.toCollection(ArrayList::new));
    }


    //pt update la baza de date
    public void updateSeats(Route route, int bookedSeats){
        this.repo.updateDB(route.getId(), route.getAvailable_seats() - bookedSeats);
        this.repo.readFromDB(); //dupa ce ai updatat baza de date citesti iar ce ai in baza de date ca sa updatezi repou cu care lucrezi
    }
}
