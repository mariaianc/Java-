package Repositories;

import Domain.FlightControl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

public class Repo <T extends FlightControl> {

    protected Vector<T> flights = new Vector<T>();

    public void add_flight(T flight)
    {
        flights.add(flight);
    }

    public void get_all()
    {
        for(T f: flights)
        {
            System.out.println(f);
        }
    }

    public void streams(int given_value) {

        flights.stream().filter((doc) -> doc.isMaintenance() && doc.getPrice() < given_value)
                .sorted((doc1, doc2) -> doc2.toString().compareTo(doc1.toString()))
                .forEach(System.out::println );
    }


    public void save_to_file() {

        try {
            FileWriter mywritter = new FileWriter("myfile.txt");

            Iterator<T> i = flights.iterator();
            while(i.hasNext())
            {
                T flight = (T)i.next();
                if(flight.isMaintenance()==true) {
                    //vec.add(flight);
                    mywritter.write(flight.toString() + '\n');
                }

            }
            mywritter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }



    }
