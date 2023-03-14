package service;

import domain.Weather;
import repository.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public ArrayList<Weather> sortByStart()
    {
        return repo.getWeathers()
                .stream()
                .sorted(Comparator.comparing(o -> o.getStart()))
                        //((a,b)->a.getStart().compareTo(b.getStart()))
                .collect(Collectors.toCollection(ArrayList::new));
    }



    public ArrayList<String> getAllDescription()
    {
        return repo
                .getWeathers()
                .stream()                            //il faci stream
                .map(w -> w.getDescription().split(",",2)[1])
                .collect(Collectors.toCollection(ArrayList::new));//chestia asta face din stream arraylist iar
    }

    /*
    public String getFirstWord(String description)
    {
        //String description = w.getDescription();
        String[] arr = description.split(",", 2);
        return arr[0];
    }

     */




}
