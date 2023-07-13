package sg.edu.rp.c346.id22022868.savesongs;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;

    //Constructors
    public Song(String title, String singers, int year, int stars) {
        this.id = 1;
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSingers() {
        return singers;
    }

    public int getYear() {
        return year;
    }

    public int getStar() {
        return stars;
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }

    public void setSinger(String newSinger) {
        this.singers = newSinger;
    }

    public void setYear(int newYear) {
        this.year = newYear;
    }

    public void setStars(int newStars) {
        this.stars = newStars;
    }



    @NonNull
    @Override
    public String toString() {

        String starString = "";
        if (stars == 1) {
            starString = "*";
        }
        else if (stars == 2) {
            starString = "**";
        }
        else if (stars == 3) {
            starString = "***";
        }
        else if (stars == 4) {
            starString = "****";
        }
        else {
            starString = "*****";
        }

        return title + "\n" + singers + "\n" + year + "\n" + starString;
    }

}
