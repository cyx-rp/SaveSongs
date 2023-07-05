package sg.edu.rp.c346.id22022868.savesongs;

import androidx.annotation.NonNull;

public class Song {

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

    @NonNull
    @Override
    public String toString() {
        return title + "\n" + singers + "\n" + year + "\n" + stars;
    }

}
