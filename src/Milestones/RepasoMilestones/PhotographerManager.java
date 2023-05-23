package Milestones.RepasoMilestones;

import javax.swing.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

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
        List<Photographer> myPhotographerList = new ArrayList<>();
        Statement myStatement = null;
        ResultSet myResultset = null;

        try {
            myStatement = myConnection.createStatement();
            myResultset = myStatement.executeQuery("SELECT * FROM Photographers");

            while (myResultset.next()) {
                int photographerId = myResultset.getInt("PhotographerId");
                String name = myResultset.getString("Name");
                boolean awarded = myResultset.getBoolean("Awarded");
                Photographer myPhotographer = new Photographer(photographerId, name, awarded);
                myPhotographerList.add(myPhotographer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (myResultset != null) {
                    myResultset.close();
                }
                if (myStatement != null) {
                    myStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return myPhotographerList;
    }

    public void remove() {
        try (Statement myStatement = myConnection.createStatement();
             ResultSet myResultset = myStatement.executeQuery("SELECT * FROM Pictures")) {

            while (myResultset.next()) {
                int pictureId = myResultset.getInt("PictureId");
                String file = myResultset.getString("File");
                int visits = myResultset.getInt("Visits");
                int photographerId = myResultset.getInt("PhotographerId");

                System.out.println(pictureId + ": " + visits);

                if (visits == 0) {
                    int selected_option = JOptionPane.showConfirmDialog(null, "Delete " + file + "?", "Confirm delete", JOptionPane.YES_NO_OPTION);
                    if (selected_option == 0) {
                        try (PreparedStatement myPreparedStatement = myConnection.prepareStatement("DELETE FROM Pictures WHERE PictureId = ?")) {
                            myPreparedStatement.setInt(1, pictureId);
                            myPreparedStatement.executeUpdate();
                            System.out.println("First option");

                            Map<Integer, Integer> myVisitsMap = visitsMap();
                            Iterator<Integer> it = myVisitsMap.keySet().iterator();
                            while (it.hasNext()) {
                                int id = it.next();
                                int vists = myVisitsMap.get(id);
                                if (vists == 0 && id == photographerId) {
                                    int selected_option2 = JOptionPane.showConfirmDialog(null, "Delete Photographer?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                                    if (selected_option2 == 0) {
                                        try (PreparedStatement myPreparedStatement2 = myConnection.prepareStatement("DELETE FROM Photographers WHERE PhotographerId = ?")) {
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
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }


    public List<Picture> getPicture(int photographerIndex, Date datePicker) {
        List<Picture> myPictureList = new ArrayList<Picture>();
        PreparedStatement myStatement = null;
        ResultSet myResultset = null;

        try {
            if (datePicker != null) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String datePickerString = df.format(datePicker);
                System.out.println(datePickerString);

                myStatement = myConnection.prepareStatement(
                        "SELECT * FROM Pictures WHERE PhotographerId = ? AND Date > ?");
                myStatement.setInt(1, getPhotographers().get(photographerIndex).getPhotographerID());
                myStatement.setString(2, datePickerString);
            } else {
                myStatement = myConnection.prepareStatement(
                        "SELECT * FROM Pictures WHERE PhotographerId = ?");
                myStatement.setInt(1, getPhotographers().get(photographerIndex).getPhotographerID());
            }

            myResultset = myStatement.executeQuery();

            while (myResultset.next()) {
                int pictureId = myResultset.getInt("PictureId");
                String title = myResultset.getString("Title");
                Date date = myResultset.getDate("Date");
                String file = myResultset.getString("File");
                int visits = myResultset.getInt("Visits");
                int photographerId = myResultset.getInt("PhotographerId");

                Photographer photographer = getPhotographers().get(photographerIndex);
                Picture picture = new Picture(pictureId, title, date, file, visits, photographer);
                myPictureList.add(picture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myResultset != null) {
                try {
                    myResultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return myPictureList;
    }

    public Map<Integer, Integer> visitsMap() {
        Map<Integer, Integer> myVisitMap = new HashMap<>();
        Statement myStatement = null;
        ResultSet myResultset = null;

        try {
            myStatement = myConnection.createStatement();
            myResultset = myStatement.executeQuery("SELECT * FROM Pictures;");

            while (myResultset.next()) {
                int photographerId = myResultset.getInt("PhotographerId");
                int visits = myResultset.getInt("Visits");

                if (myVisitMap.containsKey(photographerId)) {
                    visits += myVisitMap.get(photographerId);
                }

                System.out.println(photographerId + ": " + visits);
                myVisitMap.put(photographerId, visits);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (myResultset != null) {
                try {
                    myResultset.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (myStatement != null) {
                try {
                    myStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return myVisitMap;
    }

    public void award(int minVisits) {
        Map<Integer, Integer> myVisitsMap = visitsMap();

        try (PreparedStatement myStatement = myConnection.prepareStatement(
                "UPDATE Photographers SET Awarded = Awarded + 1 WHERE PhotographerId = ?")) {

            for (int photographerId : myVisitsMap.keySet()) {
                int visits = myVisitsMap.get(photographerId);

                if (visits >= minVisits) {
                    myStatement.setInt(1, photographerId);
                    myStatement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}




