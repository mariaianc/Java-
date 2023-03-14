import Domain.FlightControl;
import Repositories.Repo;
import UI.UI;

public class Main {
    public static void main(String [] args)
    {
        Repo repo = new Repo();
        UI ui = new UI(repo);
        ui.start();

    }
}
