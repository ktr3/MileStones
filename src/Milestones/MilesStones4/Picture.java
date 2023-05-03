package Milestones.MilesStones4;

/*
package Exercise2;

import java.util.Date;

public class Picture {
    private int id;
    private String title;
    private Date date;
    private String file;
    private int visits;
    private int photographerId;

    public Picture(int id, String title, Date date, String file, int visits, int photographerId) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.file = file;
        this.visits = visits;
        this.photographerId = photographerId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getFile() {
        return file;
    }

    public int getVisits() {
        return visits;
    }

    public void incrementVisits() {
        visits++;
    }

    public int getPhotographerId() {
        return photographerId;
    }

    public Photographer getPhotographer() {
        return DatabaseManager.getPhotographerById(photographerId);
    }

    @Override
    public String toString() {
        return title;
    }
}
*/


import java.util.Date;

public class Picture {

    private int pictureId;
    private String title;
    private Date date;
    private String file;
    private int visits;
    private Photographer photographer;

    public Picture(int pictureId, String title, Date date, String file, int visits, Photographer photographer) {
        this.pictureId = pictureId;
        this.title = title;
        this.date = date;
        this.file = file;
        this.visits = visits;
        this.photographer = photographer;
    }

    public int getPictureId() {
        return pictureId;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public String getFile() {
        return file;
    }

    public int getVisits() {
        return visits;
    }

    public Photographer getPhotographer() {
        return photographer;
    }
}