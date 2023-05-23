package Milestones.RepasoMilestones;
import java.util.Date;
public class Picture {
    private int pictureId;
    private Date date;
    private String file;
    private int visits;
    private Photographer photographer;

    public Picture(int pictureId, Date date, String file, int visits, Photographer photographer) {
        this.pictureId = pictureId;
        this.date = date;
        this.file = file;
        this.visits = visits;
        this.photographer = photographer;
    }

    public int getPictureId() {
        return pictureId;
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
