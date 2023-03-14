package repository;

import domain.Route;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private ArrayList<Route> routes = new ArrayList<>();
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public static Connection getConnection() {
        if (conn == null)
            openConnection();
        return conn;
    }

    private static void openConnection()
    {
        try
        {
            SQLiteDataSource ds = new SQLiteDataSource();
            ds.setUrl(JDBC_URL);
            if (conn == null || conn.isClosed())
                conn = ds.getConnection();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection()
    {
        try
        {
            conn.close();
            conn = null;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    //asa iti pui chestiile in baza de date
    public void readFromDB()
    {
        if(conn==null) openConnection();


        try {
            Statement stmt = conn.createStatement();   //var scrii cand vrei orice iti returneaza functia, ca si auto in c++
            ResultSet rs = stmt.executeQuery("SELECT * FROM Routes");  // ResultSet makes the result of a select instruction
            while(rs.next())//moves the cursor to the next row
            {
                //iti ia pe rand fiecare coloana din baza de date si iti pune ce e acolo in o variabila cu care creeaza dupa obiectul
                int id = rs.getInt("id");
                String source_city = rs.getString("source_city");
                String destination_city = rs.getString("destination_city");
                LocalTime departure_time = LocalTime.parse(rs.getString("departure_time"));
                LocalTime arrival_time = LocalTime.parse(rs.getString("arrival_time"));
                int available_seats = rs.getInt("available_seats");
                int price_ticket = rs.getInt("price_ticket");

                //citeste pe rand fiecare obiect din baza de date si il creeaza
                Route newRoute = new Route(id,source_city,destination_city,departure_time,arrival_time,available_seats,price_ticket);
                routes.add(newRoute); //le pui in repo ca sa le folosesti mai tz
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateDB(int routeId, int newSeats)
    {
        if (conn == null) openConnection();
        try {
            PreparedStatement statement = conn.prepareStatement("UPDATE Routes SET available_seats = ? WHERE id = ? ");
            statement.setInt(1, newSeats); //pui in ordine ce iti trb pe la semnele de intrebare
            statement.setInt(2, routeId);

            statement.executeUpdate();
            //statement.executeQuery();  asa da eroare
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
