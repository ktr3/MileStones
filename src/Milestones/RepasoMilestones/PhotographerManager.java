package Milestones.RepasoMilestones;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.security.PublicKey;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PhotographerManager {

    private Connection myConnection;
    private static final String driver = "org.mariadb.jdbc.Driver";
    private static final String link = "jdbc:mariadb://localhost:3306/ktr3";
    private static final String user = "root";
    private static final String pass = "root";

    public PhotographerManager() {
        myConnection = null;
        try {
            Class.forName(driver);
            myConnection = DriverManager.getConnection(link, user, pass);
            System.out.println("Connected!");
        } catch (ClassNotFoundException e) {
            System.out.println("Not connected class");
        } catch (SQLException e) {
            System.out.println("Not connected sql");
        }
        visitsMap();
    }

    public void closeCon() {
        if (myConnection != null) {
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

            if (myStatement.execute("SELECT * FROM Photographers;")) {
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

            if (myStatement.execute("SELECT * FROM Pictures;")) {
                myResultset = myStatement.getResultSet();
                while (myResultset.next()) {
                    int pictureId = myResultset.getInt("PictureId");
                    String title = myResultset.getString("Titel");
                    Date date = myResultset.getDate("Date");
                    String file = myResultset.getString("File");
                    int visits = myResultset.getInt("Visists");
                    int photographerId = myResultset.getInt("PhotographerId");

                    System.out.println(pictureId + ": " + visits);

                    if (visits == 0) {
                        int selected_option = JOptionPane.showConfirmDialog(null, "Delete"
                                + file + "?", "Confir delete", JOptionPane.YES_NO_OPTION);
                        if (selected_option == 0) {

                            PreparedStatement myPreparedStatement = myConnection.prepareStatement(
                                    "DELETE FROM Pictures WHERE PictureId = ?");
                            myPreparedStatement.setInt(1, pictureId);
                            myPreparedStatement.executeUpdate();
                            System.out.println("Firstoption");

                            Map<Integer, Integer> myVisitsMap = visitsMap();
                            Iterator<Integer> it = myVisitsMap.keySet().iterator();
                            while (it.hasNext()) {
                                int id = it.next();
                                int vists = myVisitsMap.get(id);
                                if (vists == 0) {
                                    int selected_option2 = JOptionPane.showConfirmDialog(null,
                                            "Delete Photographer?", "confirm Delete", JOptionPane.YES_NO_OPTION);
                                    if (selected_option2 == 0) {
                                        PreparedStatement myPreparedStatement2 = myConnection.prepareStatement(
                                                "DELETE FROM Photographers WHERE PhotographerId = ?");
                                        myPreparedStatement2.setInt(1, photographerId);
                                        myPreparedStatement2.executeUpdate();
                                        FileWriter foS = new FileWriter("secondoption.txt");
                                        foS.write("otra vez!");
                                        foS.close();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public List<Picture> getPicture(int photographerIndex, Date datePicker) {
        List<Picture> myPictureList = new ArrayList<Picture>();
        PreparedStatement myStatement = null;
        ResultSet myResultset = null;

        if (datePicker != null) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String datePickerString = df.format(datePicker);
            System.out.println(datePickerString);
            ;
            myStatement = myConnection.prepareStatement(
                    "SELECT * FROM Pictures Where PhotographerId = ?" + "AND Date > ?");
            myStatement.setInt(1, this.getPhotographers().get(photographerIndex).getPhotographerID());
            myStatement.setString(2, datePickerString);
        } else {
            myStatement = myConnection.prepareStatement(
                    "SELECT * FROM Picture WHERE PhotographerId = ?;");
            myStatement.setInt(1, this.getPhotographers().get(photographerIndex).getPhotographerID());
        }
        myResultset = myStatement.executeQuery();

        while (myResultset.next()){
            int pictureId = myResultset.getInt("PictureId");
            int pictureId = myResultset.getInt("PictureId");
            String title = myResultset.getString("Titel");
            Date date = myResultset.getDate("Date");
            String file = myResultset.getString("File");
            int visits = myResultset.getInt("Visists");
            int photographerId = myResultset.getInt("PhotographerId");
        }

    }

}