package repository;

import domain.Document;
import org.sqlite.SQLiteDataSource;
import java.sql.*;
import java.time.LocalTime;
import java.util.ArrayList;

public class Repository {
    private static final String JDBC_URL = "jdbc:sqlite:data/test_db.db";

    private static Connection conn = null;

    private ArrayList<Document> documents = new ArrayList<>();

    public ArrayList<Document> getDocuments()
    {
        return documents;
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
            this.documents = new ArrayList<>();
            Statement stmt = conn.createStatement();   //var scrii cand vrei orice iti returneaza functia, ca si auto in c++
            ResultSet rs = stmt.executeQuery("SELECT * FROM Documents");  // ResultSet makes the result of a select instruction
            while(rs.next())//moves the cursor to the next row
            {
                //iti ia pe rand fiecare coloana din baza de date si iti pune ce e acolo in o variabila cu care creeaza dupa obiectul
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String keyWords = rs.getString("keyWords");
                String content = rs.getString("content");


                //citeste pe rand fiecare obiect din baza de date si il creeaza
                Document doc = new Document(id,name,keyWords,content);
                documents.add(doc); //le pui in repo ca sa le folosesti mai tz
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //modifica
    public void updateDB(int documentId, String new_keys, String new_content )
    {
        if (conn == null) openConnection();
        try {
            /*PreparedStatement statement = conn.prepareStatement("UPDATE Documents SET keyWords = ?, content = ? WHERE id = ? ");
            statement.setString(1, new_keys); //pui in ordine ce iti trb pe la semnele de intrebare
            statement.setString(2, new_content);
            statement.setInt(3,documentId);

            statement.executeUpdate();*/

            String SQLStatement = "UPDATE Documents SET keyWords = '" + new_keys + "', content = '" + new_content + "' WHERE id = " + String.valueOf(documentId)+";";
            Statement stat = conn.createStatement();
            stat.executeUpdate(SQLStatement);

            //statement.close();      //trebuie sa fac asta? de ce
            //statement.executeQuery();  asa da eroare
            //closeConnection();      //trebuie sa inchid conexiunea?
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
