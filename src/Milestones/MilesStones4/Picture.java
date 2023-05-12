package Milestones.MilesStones4;

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