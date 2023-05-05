package Milestones.MilesStones4;

/*
package Exercise2;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {
        private static final String URL = "jdbc:mariadb://localhost:3306/kevin";
        private static final String USERNAME = "root";
        private static final String PASSWORD = "root";

        private static Connection connection;

        public static Connection getConnection() throws SQLException {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            return connection;
        }


        public static List<Photographer> getPhotographers() throws SQLException {
            List<Photographer> photographers = new ArrayList<>();
            String query = "SELECT * FROM Photographers";
            try (Statement statement = getConnection().createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("PhotographerId");
                    String name = resultSet.getString("Name");
                    boolean awarded = resultSet.getBoolean("Awarded");
                    Photographer photographer = new Photographer(id, name, awarded);
                    photographers.add(photographer);
                }
            }
            return photographers;
        }

        public static List<Picture> getPictures(int photographerId, Date date) throws SQLException {
            List<Picture> pictures = new ArrayList<>();
            String query = "SELECT * FROM Pictures WHERE PhotographerId = ? AND Date >= ?";
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, photographerId);
                preparedStatement.setDate(2, date);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("PictureId");
                        String title = resultSet.getString("Title");
                        Date pictureDate = resultSet.getDate("Date");
                        String file = resultSet.getString("File");
                        int visits = resultSet.getInt("Visits");
                        Picture picture = new Picture(id, title, pictureDate, file, visits, photographerId);
                        pictures.add(picture);
                    }
                }
            }
            return pictures;
        }
    public static Photographer getPhotographerById(int id) {
        String query = "SELECT * FROM Photographers WHERE PhotographerId = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("Name");
                boolean awarded = resultSet.getBoolean("Awarded");

                return new Photographer(id, name, awarded);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

        public static void incrementVisits(Picture picture) throws SQLException {
            String query = "UPDATE Pictures SET Visits = Visits + 1 WHERE PictureId = ?";
            try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
                preparedStatement.setInt(1, picture.getId());
                preparedStatement.executeUpdate();
            }
        }

    public static List<Picture> getPictures() {
        return null;
    }
}*/


import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class PhotographerManager {

    private Connection myConnection;
    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String url = "jdbc:mariadb://localhost:3306/kevin";
    private static final String username = "root";
    private static final String password = "root";

    public PhotographerManager () {
        myConnection = null;
        try {
            Class.forName(driver);
            myConnection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected!");
        } catch (ClassNotFoundException e) {
            System.out.println("NOT CONECTED");
        } catch (SQLException e) {
            System.out.println("NOT CONECTED 2");
        }
        visitsMap();
    }

    public void closeCon () {
        if(myConnection != null){
            try {
                myConnection.close();
                System.out.println("Disconnected!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Photographer> getPhotographers() {
        List<Photographer> myPhotographerList = new ArrayList<Photographer>();
        Statement myStatement = null;
        ResultSet myResultset = null;

        try {
            myStatement = myConnection.createStatement();

            if(myStatement.execute("SELECT * FROM Photographers;")) {
                myResultset = myStatement.getResultSet();

                while (myResultset.next()) {
                    int photographerId = myResultset.getInt("PhotographerId");
                    String name = myResultset.getString("Name");
                    Boolean awarded = myResultset.getBoolean("Awarded");
                    Photographer myPhotographer = new Photographer(photographerId, name, awarded);
                    myPhotographerList.add(myPhotographer);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myPhotographerList;
    }

    public void remove() {
        List<Picture> myAllPictureList = new ArrayList<Picture>();
        Statement myStatement = null;
        ResultSet myResultset = null;

        try {
            myStatement = myConnection.createStatement();
            if(myStatement.execute("SELECT * FROM Pictures;")){
                myResultset = myStatement.getResultSet();

                while(myResultset.next()){
                    int pictureId = myResultset.getInt("PictureId");
                    String title = myResultset.getString("Title");
                    Date date = myResultset.getDate("Date");
                    String file = myResultset.getString("File");
                    int visits = myResultset.getInt("Visits");
                    int photographerId = myResultset.getInt("PhotographerId");

                    System.out.println(pictureId + ": " + visits);

                    if(visits == 0) {
                        int selected_option= JOptionPane.showConfirmDialog(null,"Delete " + file + "?","Confirm delete",JOptionPane.YES_NO_OPTION);
                        if(selected_option == 0) {

                            Map<Integer, Integer> myVisitsMap = visitsMap();
                            Iterator<Integer> it = myVisitsMap.keySet().iterator();
                            while(it.hasNext()){
                                int id = it.next();
                                int vists = myVisitsMap.get(id);
                                if(vists == 0) {
                                    int selected_option2 = JOptionPane.showConfirmDialog(null,"Delete Photographer?","Confirm Delete",JOptionPane.YES_NO_OPTION);
                                    if(selected_option2 == 0) {
                                        PreparedStatement myPreparedStatement2 = myConnection.prepareStatement("DELETE FROM Photographers WHERE PhotographerId = ?");
                                        myPreparedStatement2.setInt(1, photographerId);
                                        myPreparedStatement2.executeUpdate();
                                        FileWriter foS = new FileWriter("secondoption.txt");
                                        foS.write("otra vez!");
                                        foS.close();
                                    }
                                }
                            }
                            PreparedStatement myPreparedStatement = myConnection.prepareStatement("DELETE FROM Pictures WHERE PictureId = ?");
                            myPreparedStatement.setInt(1, pictureId);
                            myPreparedStatement.executeUpdate();
                            System.out.println("firstoption");
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Picture> getPictures(int photographerIndex, Date datePicker) {
        List<Picture> myPictureList = new ArrayList<Picture>();
        PreparedStatement myStatement = null;
        ResultSet myResultset = null;

        try {
            if(datePicker != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String datePickerString = df.format(datePicker);
                System.out.println(datePickerString);
                myStatement = myConnection.prepareStatement("SELECT * FROM Pictures WHERE PhotographerId = ? AND Date > ?");
                myStatement.setInt(1, this.getPhotographers().get(photographerIndex).getPhotographerId());
                myStatement.setString(2, datePickerString);
            } else {
                myStatement = myConnection.prepareStatement("SELECT * FROM Pictures WHERE PhotographerId = ?;");
                myStatement.setInt(1, this.getPhotographers().get(photographerIndex).getPhotographerId());
            }
            myResultset = myStatement.executeQuery();

            while(myResultset.next()){
                int pictureId = myResultset.getInt("PictureId");
                String title = myResultset.getString("tTtle");
                Date date = myResultset.getDate("Date");
                String file = myResultset.getString("File");
                int visits = myResultset.getInt("Visits");
                int photographerId = myResultset.getInt("PhotographerId");

                myPictureList.add(new Picture(pictureId, title, date, file, visits, this.getPhotographers().get(photographerIndex)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myPictureList;
    }

    public Map<Integer, Integer> visitsMap() {
        Map<Integer, Integer> myVisitsMap = new HashMap<Integer, Integer>();
        Statement myStatement = null;
        ResultSet myResultset = null;

        try {
            myStatement = myConnection.createStatement();

            if(myStatement.execute("SELECT * FROM Pictures;")){
                myResultset = myStatement.getResultSet();

                while(myResultset.next()) {
                    int pictureId = myResultset.getInt("PictureId");
                    String title = myResultset.getString("Title");
                    Date date = myResultset.getDate("Date");
                    String file = myResultset.getString("File");
                    int visits = myResultset.getInt("Visits");
                    int photographerId = myResultset.getInt("PhotographerId");

                    if(myVisitsMap.containsKey(photographerId)) {
                        visits += myVisitsMap.get(photographerId);
                    }
                    System.out.println(photographerId + ": " + visits);
                    myVisitsMap.put(photographerId, visits);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return myVisitsMap;
    }

    public void award(int minVisits) {
        Map<Integer, Integer> myVisitsMap = visitsMap();
        Iterator<Integer> it = myVisitsMap.keySet().iterator();
        while(it.hasNext()){
            int photographerId = it.next();
            int visits = myVisitsMap.get(photographerId);
            if(visits >= minVisits) {
                try {
                    PreparedStatement myStatement = myConnection.prepareStatement("SELECT * FROM Photographers WHERE PhotographerId = ?;");
                    myStatement.setInt(1, photographerId);
                    ResultSet myResultset = myStatement.executeQuery();
                    myResultset.next();
                    int awards = myResultset.getInt("Awarded");

                    myStatement = myConnection.prepareStatement("UPDATE Photographers SET Awarded = ? WHERE PhotographerId = ?");
                    myStatement.setInt(1, (awards+1));
                    myStatement.setInt(2,photographerId);
                    myStatement.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}