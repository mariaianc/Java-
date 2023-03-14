package UI;

import java.util.Scanner;
import java.util.concurrent.Callable;

import Domain.ControlSoftware;
import Domain.FlightControl;
import Domain.Instrument;
import Repositories.*;

public class UI {

    protected Repo repo;

    public UI(Repo repo) {
        this.repo = repo;
    }

    private ControlSoftware create_soft()
    {
        System.out.println("create a soft: ");
        Scanner input = new Scanner(System.in);
        System.out.println("code: ");
        String code = input.nextLine();
        System.out.println("maintenance: ");
        boolean maintenance = Boolean.parseBoolean(input.nextLine());
        System.out.println("version: ");
        int version = input.nextInt();
        input.nextLine();

        ControlSoftware software = new ControlSoftware(code, maintenance, version);
        return software;
    }

    private Instrument create_instrument()
    {
        System.out.println("create a instrument: ");
        Scanner input = new Scanner(System.in);
        System.out.println("code: ");
        String code = input.nextLine();
        System.out.println("maintenance: ");
        boolean maintenance = Boolean.parseBoolean(input.nextLine());
        System.out.println("measurement: ");
        String measurement = input.nextLine();
        input.nextLine();

        Instrument instrument= new Instrument(code, maintenance, measurement);
        return instrument;
    }


    private void menu()
    {
        System.out.println("Menu");
        System.out.println("1. add software");
        System.out.println("2. add instrument");
        System.out.println("3. get all");
        System.out.println("4. streams");
        System.out.println("5. save to file");
        System.out.println("0. exit");
    }

    public void start()
    {
        Scanner input = new Scanner(System.in);

        ControlSoftware c1 = new ControlSoftware("1234", true, 9);//price 100
        ControlSoftware c2 = new ControlSoftware("3333", true, 11);//price 200
        ControlSoftware c3 = new ControlSoftware("1411", false, 5);//price 100 but no maintenance
        ControlSoftware c4 = new ControlSoftware("812", true, 5);//price 100

        repo.add_flight(c1);
        repo.add_flight(c2);
        repo.add_flight(c3);
        repo.add_flight(c4);

        Instrument i1 = new Instrument("5555", true, "altitude"); //p 50
        Instrument i2 = new Instrument("719", false, "altitude");//p 50 but false
        Instrument i3 = new Instrument("8240", true, "direction");//p 50
        Instrument i4 = new Instrument("001", true, "engine_state");//p 500

        repo.add_flight(i1);
        repo.add_flight(i2);
        repo.add_flight(i3);
        repo.add_flight(i4);


        while(true)
        {
            this.menu();

            int option = input.nextInt();
            input.nextLine();

            switch(option)
            {
                case 1:
                    ControlSoftware soft = create_soft();
                    repo.add_flight(soft);
                    break;


                case 2:
                    Instrument instrument = create_instrument();
                    repo.add_flight(instrument);

                case 3:
                    repo.get_all();
                    break;

                case 4:
                    int given_val = input.nextInt();
                    repo.streams(given_val);
                    break;

                case 5:
                    repo.save_to_file();
                    break;
                case 0:
                    return;
            }



        }
    }

}
