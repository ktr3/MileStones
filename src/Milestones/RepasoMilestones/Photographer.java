package Milestones.RepasoMilestones;

public class Photographer {

    private int photographerID;
    private String name;
    private boolean awarded;


    public Photographer(int photographerID, String name, boolean awarded) {
        this.photographerID = photographerID;
        this.name = name;
        this.awarded = awarded;
    }

    public int getPhotographerID() {
        return photographerID;
    }

    public String getName() {
        return name;
    }

    public boolean isAwarded(){
        return awarded;
    }


    @Override
    public String toString() {
        return "Photographer{" +
                "photographerID=" + photographerID +
                ", name='" + name + '\'' +
                ", awarded=" + awarded +
                '}';
    }
}
