package repository;

import domain.Weather;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private ArrayList<Weather> weathers = new ArrayList<>();

    public ArrayList<Weather> getWeathers()
    {
        return weathers;
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

    public void readFromDB()
    {
        if(conn==null) openConnection();
        try {

            this.weathers = new ArrayList<>();

            Statement stmt = conn.createStatement();   //var scrii cand vrei orice iti returneaza functia, ca si auto in c++
            ResultSet rs = stmt.executeQuery("SELECT * FROM Weather");  // ResultSet makes the result of a select instruction
            while(rs.next())//moves the cursor to the next row
            {
                //iti ia pe rand fiecare coloana din baza de date si iti pune ce e acolo in o variabila cu care creeaza dupa obiectul
                int id = rs.getInt("id");
                int start = rs.getInt("start");
                int end = rs.getInt("end");
                int temperature = rs.getInt("temperature");
                int prec_prob = rs.getInt("prec_prob");
                String description = rs.getString("description");

                Weather newWeather = new Weather(id,start,end,temperature,prec_prob,description);
                weathers.add(newWeather);

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
